package com.cdl.escrow.repository;

import com.cdl.escrow.entity.OwnerRegistryUnitBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryUnitBookingRepository extends JpaRepository<OwnerRegistryUnitBooking,Long> , JpaSpecificationExecutor<OwnerRegistryUnitBooking> {
    Optional<OwnerRegistryUnitBooking> findByIdAndDeletedFalse(Long id);
}
