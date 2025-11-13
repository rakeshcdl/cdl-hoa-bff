package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BudgetMapper;
import com.cdl.escrow.repository.BudgetRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.BudgetService;
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
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository repository;

    private final BudgetMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public Page<BudgetDTO> getAllBudget(Pageable pageable) {
        log.debug("Fetching all Budget, page: {}", pageable.getPageNumber());
        Page<Budget> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BudgetDTO> getBudgetById(Long id) {
        log.debug("Fetching budget with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BudgetDTO saveBudget(BudgetDTO budgetDTO) {
        log.info("Saving new build partner");

        Budget entity = mapper.toEntity(budgetDTO);

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);*/

        Budget saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        log.info("Updating Budget with ID: {}", id);

        Budget existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Budget not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        Budget toUpdate = mapper.toEntity(budgetDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);*/
        Budget updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBudgetById(Long id) {
        log.info("Deleting budget  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Budget not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public void finalizeBudget(Long moduleId, TaskStatus status) {


        Budget budget =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Budget not found: " + moduleId));

        //budget.setTaskStatus(status);
        repository.save(budget);

    }

    @Override
    public boolean softBudgetServiceById(Long id) {

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
