package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.AppLanguageCodeCriteria;
import com.cdl.escrow.criteriaservice.AppLanguageCodeCriteriaService;
import com.cdl.escrow.dto.AppLanguageCodeDTO;
import com.cdl.escrow.repository.AppLanguageCodeRepository;
import com.cdl.escrow.service.AppLanguageCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppLanguageCodeController.class)
public class AppLanguageCodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AppLanguageCodeService appLanguageCodeService;

    @MockitoBean
    private AppLanguageCodeCriteriaService appLanguageCodeCriteriaService;

    @MockitoBean
    private AppLanguageCodeRepository repository;

    private AppLanguageCodeDTO dto;
    private List<AppLanguageCodeDTO> dtoList;
    private Page<AppLanguageCodeDTO> page;


    @BeforeEach
    void setUp() {
        dto = new AppLanguageCodeDTO();
        dto.setId(1L);
        dto.setLanguageCode("en");
        dto.setNameNativeValue("English");

        AppLanguageCodeDTO dto2 = new AppLanguageCodeDTO();
        dto2.setId(2L);
        dto2.setLanguageCode("es");
        dto2.setNameNativeValue("Spanish");

        dtoList = Arrays.asList(dto, dto2);
        page = new PageImpl<>(dtoList, PageRequest.of(0, 20), dtoList.size());
    }
    // ========================================
    // GET /api/v1/app-language-code (with criteria)
    // ========================================

    @Test
    @DisplayName("GET /api/v1/app-language-code - Success with Criteria")
    void getAllApplicationConfigurations_WithCriteria_Success() throws Exception {
        // Arrange
        when(appLanguageCodeCriteriaService.findByCriteria(any(AppLanguageCodeCriteria.class), any(Pageable.class)))
                .thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].languageCode", is("en")))
                .andExpect(jsonPath("$.content[0].nameNativeValue", is("English")))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[1].languageCode", is("es")))
                .andExpect(jsonPath("$.totalElements", is(2)));

        verify(appLanguageCodeCriteriaService, times(1))
                .findByCriteria(any(AppLanguageCodeCriteria.class), any(Pageable.class));
    }
    @Test
    @DisplayName("GET /api/v1/app-language-code - Empty Result")
    void getAllApplicationConfigurations_WithCriteria_EmptyResult() throws Exception {
        // Arrange
        Page<AppLanguageCodeDTO> emptyPage = new PageImpl<>(Arrays.asList(), PageRequest.of(0, 20), 0);
        when(appLanguageCodeCriteriaService.findByCriteria(any(AppLanguageCodeCriteria.class), any(Pageable.class)))
                .thenReturn(emptyPage);

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.totalElements", is(0)));

        verify(appLanguageCodeCriteriaService, times(1))
                .findByCriteria(any(AppLanguageCodeCriteria.class), any(Pageable.class));
    }

    // ========================================
    // GET /api/v1/app-language-code/find-all
    // ========================================

    @Test
    @DisplayName("GET /api/v1/app-language-code/find-all - Success")
    void getAllApplicationConfigurations_FindAll_Success() throws Exception {
        // Arrange
        when(appLanguageCodeService.getAllAppLanguageCode(any(Pageable.class))).thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code/find-all")
                        .param("page", "0")
                        .param("size", "20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].languageCode", is("en")))
                .andExpect(jsonPath("$.content[1].languageCode", is("es")))
                .andExpect(jsonPath("$.totalElements", is(2)));

        verify(appLanguageCodeService, times(1)).getAllAppLanguageCode(any(Pageable.class));
    }

    @Test
    @DisplayName("GET /api/v1/app-language-code/find-all - Default Pagination")
    void getAllApplicationConfigurations_FindAll_DefaultPagination() throws Exception {
        // Arrange
        when(appLanguageCodeService.getAllAppLanguageCode(any(Pageable.class))).thenReturn(page);

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code/find-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));

        verify(appLanguageCodeService, times(1)).getAllAppLanguageCode(any(Pageable.class));
    }

    // ========================================
    // POST /api/v1/app-language-code
    // ========================================

    @Test
    @DisplayName("POST /api/v1/app-language-code - Success")
    void saveApplicationConfiguration_Success() throws Exception {
        // Arrange
        AppLanguageCodeDTO createDto = new AppLanguageCodeDTO();
        createDto.setLanguageCode("fr");
        createDto.setNameNativeValue("French");

        AppLanguageCodeDTO savedDto = new AppLanguageCodeDTO();
        savedDto.setId(3L);
        savedDto.setLanguageCode("fr");
        savedDto.setNameNativeValue("French");

        when(appLanguageCodeService.saveAppLanguageCode(any(AppLanguageCodeDTO.class))).thenReturn(savedDto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/app-language-code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.languageCode", is("fr")))
                .andExpect(jsonPath("$.languageName", is("French")));

        verify(appLanguageCodeService, times(1)).saveAppLanguageCode(any(AppLanguageCodeDTO.class));
    }

    @Test
    @DisplayName("POST /api/v1/app-language-code - With ID (Should Fail)")
    void saveApplicationConfiguration_WithId_ThrowsException() throws Exception {
        // Arrange
        AppLanguageCodeDTO createDto = new AppLanguageCodeDTO();
        createDto.setId(1L); // ID should not be present
        createDto.setLanguageCode("fr");
        createDto.setNameNativeValue("French");

        // Act & Assert
        mockMvc.perform(post("/api/v1/app-language-code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isBadRequest());

        verify(appLanguageCodeService, never()).saveAppLanguageCode(any(AppLanguageCodeDTO.class));
    }

    @Test
    @DisplayName("POST /api/v1/app-language-code - Invalid Data (Validation Failed)")
    void saveApplicationConfiguration_InvalidData_ValidationFailed() throws Exception {
        // Arrange
        AppLanguageCodeDTO invalidDto = new AppLanguageCodeDTO();
        // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/api/v1/app-language-code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());

        verify(appLanguageCodeService, never()).saveAppLanguageCode(any(AppLanguageCodeDTO.class));
    }

    // ========================================
    // GET /api/v1/app-language-code/{id}
    // ========================================

    @Test
    @DisplayName("GET /api/v1/app-language-code/{id} - Success")
    void getApplicationConfigurationById_Success() throws Exception {
        // Arrange
        when(appLanguageCodeService.getAppLanguageCodeById(1L)).thenReturn(Optional.of(dto));

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.languageCode", is("en")))
                .andExpect(jsonPath("$.languageName", is("English")));

        verify(appLanguageCodeService, times(1)).getAppLanguageCodeById(1L);
    }

    @Test
    @DisplayName("GET /api/v1/app-language-code/{id} - Not Found")
    void getApplicationConfigurationById_NotFound() throws Exception {
        // Arrange
        when(appLanguageCodeService.getAppLanguageCodeById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/v1/app-language-code/{id}", 999L))
                .andExpect(status().isNotFound());

        verify(appLanguageCodeService, times(1)).getAppLanguageCodeById(999L);
    }

    // ========================================
    // PUT /api/v1/app-language-code/{id}
    // ========================================

    @Test
    @DisplayName("PUT /api/v1/app-language-code/{id} - Success")
    void updateApplicationConfiguration_Success() throws Exception {
        // Arrange
        AppLanguageCodeDTO updateDto = new AppLanguageCodeDTO();
        updateDto.setId(1L);
        updateDto.setLanguageCode("en-US");
        updateDto.setNameNativeValue("English (US)");

        when(repository.existsById(1L)).thenReturn(true);
        when(appLanguageCodeService.updateAppLanguageCode(eq(1L), any(AppLanguageCodeDTO.class)))
                .thenReturn(updateDto);

        // Act & Assert
        mockMvc.perform(put("/api/v1/app-language-code/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.languageCode", is("en-US")))
                .andExpect(jsonPath("$.languageName", is("English (US)")));

        verify(repository, times(1)).existsById(1L);
        verify(appLanguageCodeService, times(1)).updateAppLanguageCode(eq(1L), any(AppLanguageCodeDTO.class));
    }

    @Test
    @DisplayName("PUT /api/v1/app-language-code/{id} - ID Null in DTO")
    void updateApplicationConfiguration_IdNull_ThrowsException() throws Exception {
        // Arrange
        AppLanguageCodeDTO updateDto = new AppLanguageCodeDTO();
        updateDto.setId(null); // ID is null
        updateDto.setLanguageCode("en-US");

        // Act & Assert
        mockMvc.perform(put("/api/v1/app-language-code/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isBadRequest());

        verify(repository, never()).existsById(any());
        verify(appLanguageCodeService, never()).updateAppLanguageCode(any(), any());
    }

    @Test
    @DisplayName("PUT /api/v1/app-language-code/{id} - ID Mismatch")
    void updateApplicationConfiguration_IdMismatch_ThrowsException() throws Exception {
        // Arrange
        AppLanguageCodeDTO updateDto = new AppLanguageCodeDTO();
        updateDto.setId(2L); // Different ID
        updateDto.setLanguageCode("en-US");

        // Act & Assert
        mockMvc.perform(put("/api/v1/app-language-code/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isBadRequest());

        verify(repository, never()).existsById(any());
        verify(appLanguageCodeService, never()).updateAppLanguageCode(any(), any());
    }

    @Test
    @DisplayName("PUT /api/v1/app-language-code/{id} - Entity Not Found")
    void updateApplicationConfiguration_EntityNotFound_ThrowsException() throws Exception {
        // Arrange
        AppLanguageCodeDTO updateDto = new AppLanguageCodeDTO();
        updateDto.setId(999L);
        updateDto.setLanguageCode("en-US");

        when(repository.existsById(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(put("/api/v1/app-language-code/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isBadRequest());

        verify(repository, times(1)).existsById(999L);
        verify(appLanguageCodeService, never()).updateAppLanguageCode(any(), any());
    }

    // ========================================
    // DELETE /api/v1/app-language-code/{id}
    // ========================================

    @Test
    @DisplayName("DELETE /api/v1/app-language-code/{id} - Success")
    void deleteApplicationConfigurationById_Success() throws Exception {
        // Arrange
        when(appLanguageCodeService.deleteAppLanguageCodeById(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/app-language-code/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AppLanguageCode deleted - ID: 1")));

        verify(appLanguageCodeService, times(1)).deleteAppLanguageCodeById(1L);
    }

    @Test
    @DisplayName("DELETE /api/v1/app-language-code/{id} - Failed")
    void deleteApplicationConfigurationById_Failed() throws Exception {
        // Arrange
        when(appLanguageCodeService.deleteAppLanguageCodeById(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/app-language-code/{id}", 999L))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("AppLanguageCode deletion failed - ID: 999")));

        verify(appLanguageCodeService, times(1)).deleteAppLanguageCodeById(999L);
    }

    // ========================================
    // DELETE /api/v1/app-language-code/soft/{id}
    // ========================================

    @Test
    @DisplayName("DELETE /api/v1/app-language-code/soft/{id} - Success")
    void softDeleteApplicationConfigurationById_Success() throws Exception {
        // Arrange
        when(appLanguageCodeService.softDeleteAppLanguageCodeById(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/app-language-code/soft/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AppLanguageCode soft deleted - ID: 1")));

        verify(appLanguageCodeService, times(1)).softDeleteAppLanguageCodeById(1L);
    }

    @Test
    @DisplayName("DELETE /api/v1/app-language-code/soft/{id} - Failed")
    void softDeleteApplicationConfigurationById_Failed() throws Exception {
        // Arrange
        when(appLanguageCodeService.softDeleteAppLanguageCodeById(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/app-language-code/soft/{id}", 999L))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("AppLanguageCode soft deletion failed - ID: 999")));

        verify(appLanguageCodeService, times(1)).softDeleteAppLanguageCodeById(999L);
    }
}
