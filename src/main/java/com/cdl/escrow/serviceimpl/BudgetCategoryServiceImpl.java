package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BudgetCategoryDTO;
import com.cdl.escrow.entity.BudgetCategory;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BudgetCategoryMapper;
import com.cdl.escrow.repository.BudgetCategoryRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.BudgetCategoryService;
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
public class BudgetCategoryServiceImpl implements BudgetCategoryService {

    private final BudgetCategoryRepository repository;

    private final BudgetCategoryMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public Page<BudgetCategoryDTO> getAllBudgetCategory(Pageable pageable) {
        log.debug("Fetching all Budget category, page: {}", pageable.getPageNumber());
        Page<BudgetCategory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BudgetCategoryDTO> getBudgetCategoryById(Long id) {
        log.debug("Fetching budget category with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BudgetCategoryDTO saveBudgetCategory(BudgetCategoryDTO budgetCategoryDTO) {
        log.info("Saving new budget category ");

        BudgetCategory entity = mapper.toEntity(budgetCategoryDTO);

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);*/

        BudgetCategory saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BudgetCategoryDTO updateBudgetCategory(Long id, BudgetCategoryDTO budgetCategoryDTO) {
        log.info("Updating Budget category with ID: {}", id);

        BudgetCategory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Budget Category not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BudgetCategory toUpdate = mapper.toEntity(budgetCategoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);*/
        BudgetCategory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBudgetCategoryById(Long id) {
        log.info("Deleting budget category with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Budget category not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public void finalizeBudgetCategory(Long moduleId, TaskStatus status) {

        BudgetCategory budget =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Budget category not found: " + moduleId));

        //budget.setTaskStatus(status);
        repository.save(budget);
    }

    @Override
    public boolean softBudgetCategoryServiceById(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException.ResourceNotFoundException("Entity not found: " + id));

        if (Boolean.TRUE.equals(entity.getDeleted())) {
            throw new ApplicationConfigurationNotFoundException.ConflictException("Entity already deleted: " + id);
        }

        entity.setDeleted(true);
        repository.save(entity);
        return true;
    }
}
