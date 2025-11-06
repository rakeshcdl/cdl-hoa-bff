package com.cdl.escrow.repository;


import com.cdl.escrow.entity.AssetRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRegisterRepository extends JpaRepository<AssetRegister,Long> , JpaSpecificationExecutor<AssetRegister> {
    Optional<AssetRegister> findByIdAndDeletedFalse(Long id);

   /* @org.jetbrains.annotations.NotNull
    @EntityGraph(attributePaths = {
            "bpRegulator.languageTranslation",
            "bpActiveStatus.languageTranslation",
            "taskStatus",
            "buildPartnerContacts",
            "buildPartnerFees",
            "buildPartnerAccounts",
            "buildPartnerBeneficiary"
    })
    java.util.Optional<BuildPartner> findById(@NotNull Long id);*/
}
