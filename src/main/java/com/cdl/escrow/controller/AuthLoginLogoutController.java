
package com.cdl.escrow.controller;

import com.cdl.escrow.dto.LoginRequestDTO;
import com.cdl.escrow.dto.LogoutRequestDTO;
import com.cdl.escrow.helper.RefreshRequestDTO;
import com.cdl.escrow.helper.TokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthLoginLogoutController {

    @Value("${keycloak.admin.server-url}")
    private String serverUrl;

    @Value("${keycloak.admin.realm}")
    private String realm;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    private final ObjectMapper objectMapper;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequest) throws IOException, InterruptedException {
        log.info("login called");

        String tokenUrl = serverUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "password");

        // Use request values or fallback to config
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("username", loginRequest.getUsername());
        params.put("password", loginRequest.getPassword());

        String formBody = params.entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            String errorMessage = String.format(
                    "Login failed for user '%s' in realm '%s' with status code: %d and response: %s",
                    loginRequest.getUsername(), realm, response.statusCode(), response.body()
            );
            throw new RuntimeException(errorMessage);
        }

        return ResponseEntity.ok(objectMapper.readValue(response.body(), AccessTokenResponse.class));
    }


    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody LogoutRequestDTO logoutRequest) {
        String logoutUrl = "https://103.181.200.143:8443/realms/cdl_rera/protocol/openid-connect/logout";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("refresh_token", logoutRequest.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForEntity(logoutUrl, request, String.class);
            return ResponseEntity.ok("Logout successful");
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody RefreshRequestDTO refreshRequest) {

        // 🔹 Your Keycloak token endpoint
        String tokenUrl = "https://103.181.200.143:8443/realms/cdl_rera/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON)); // ✅ important to avoid 406 errors

        // 🔹 Body parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("refresh_token", refreshRequest.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // 🔹 POST call to Keycloak’s /token endpoint
            ResponseEntity<TokenResponse> response =
                    restTemplate.postForEntity(tokenUrl, request, TokenResponse.class);

            // ✅ Return new tokens
            return ResponseEntity.ok(response.getBody());

        } catch (HttpClientErrorException e) {
            // 🔹 Catch 400/401 errors, typically invalid_grant when refresh token expired
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of(
                            "error", "refresh_failed",
                            "details", e.getResponseBodyAsString()
                    ));
        } catch (Exception ex) {
            // 🔹 Catch network or parsing errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "internal_error", "message", ex.getMessage()));
        }
    }
}
