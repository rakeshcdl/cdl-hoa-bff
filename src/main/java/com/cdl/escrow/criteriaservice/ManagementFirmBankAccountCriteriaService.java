package com.cdl.escrow.criteriaservice;



import com.cdl.escrow.criteria.ManagementFirmBankAccountCriteria;
import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import com.cdl.escrow.entity.ManagementFirmBankAccount;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmBankAccountMapper;
import com.cdl.escrow.repository.ManagementFirmBankAccountRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagementFirmBankAccountCriteriaService extends BaseSpecificationBuilder<ManagementFirmBankAccount>  implements Serializable {

    private final transient ManagementFirmBankAccountRepository managementFirmBankAccountRepository;

    private final transient ManagementFirmBankAccountMapper managementFirmBankAccountMapper;

    public Page<ManagementFirmBankAccountDTO> findByCriteria(ManagementFirmBankAccountCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmBankAccount> specification = createSpecification(criteria);
        return managementFirmBankAccountRepository.findAll(specification, pageable).map(managementFirmBankAccountMapper::toDto);
    }

    private Specification<ManagementFirmBankAccount> createSpecification(ManagementFirmBankAccountCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria != null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "accountType", criteria.getAccountType(), true);
                addStringFilter(cb, root, predicates, "accountNumber", criteria.getAccountNumber(), true);
                addStringFilter(cb, root, predicates, "ibanNumber", criteria.getIbanNumber(), true);
                addZonedDateTimeFilter(cb, root, predicates, "dateOpened", criteria.getDateOpened());
                addStringFilter(cb, root, predicates, "accountTitle", criteria.getAccountTitle(), true);
                addStringFilter(cb, root, predicates, "currencyCode", criteria.getCurrencyCode(), true);
                addBooleanFilter(cb, root, predicates, "isValidated", criteria.getIsValidated());
                addZonedDateTimeFilter(cb, root, predicates, "createdAt", criteria.getCreatedAt());
                addZonedDateTimeFilter(cb, root, predicates, "updatedAt", criteria.getUpdatedAt());

                // WorkflowStatus enum
                if (criteria.getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), criteria.getStatus()));
                }

                // Boolean enabled
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());

                // Relation Join
                if (criteria.getRealEstateAssestId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "realEstateAssest", "id", criteria.getRealEstateAssestId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
