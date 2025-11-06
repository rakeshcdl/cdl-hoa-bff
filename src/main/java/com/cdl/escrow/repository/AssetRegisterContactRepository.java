package com.cdl.escrow.repository;

import com.cdl.escrow.entity.AssetRegisterContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRegisterContactRepository extends JpaRepository<AssetRegisterContact,Long>, JpaSpecificationExecutor<AssetRegisterContact> {
    Optional<AssetRegisterContact> findByIdAndDeletedFalse(Long id);
}
