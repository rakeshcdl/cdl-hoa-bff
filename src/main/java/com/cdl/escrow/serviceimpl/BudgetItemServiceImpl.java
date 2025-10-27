package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.entity.BudgetItem;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BudgetItemMapper;
import com.cdl.escrow.repository.BudgetItemRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.BudgetItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetItemServiceImpl  implements BudgetItemService {

    private final BudgetItemRepository repository;

    private final BudgetItemMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public Page<BudgetItemDTO> getAllBudgetItem(Pageable pageable) {
        log.debug("Fetching all Budget item, page: {}", pageable.getPageNumber());
        Page<BudgetItem> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BudgetItemDTO> getBudgetItemById(Long id) {
        log.debug("Fetching budget item with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BudgetItemDTO saveBudgetItem(BudgetItemDTO budgetItemDTO) {
        log.info("Saving new budget category ");

        BudgetItem entity = mapper.toEntity(budgetItemDTO);

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);*/

        BudgetItem saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BudgetItemDTO updateBudgetItem(Long id, BudgetItemDTO budgetItemDTO) {
        log.info("Updating item category with ID: {}", id);

        BudgetItem existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Budget item not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BudgetItem toUpdate = mapper.toEntity(budgetItemDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);*/
        BudgetItem updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBudgetItemById(Long id) {
        log.info("Deleting budget item with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Budget item not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public void finalizeBudgetItem(Long moduleId, TaskStatus status) {
        BudgetItem budget =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Budget item not found: " + moduleId));

        //budget.setTaskStatus(status);
        repository.save(budget);
    }

    @Override
    public boolean softBudgetItemServiceById(Long id) {

        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            // entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
