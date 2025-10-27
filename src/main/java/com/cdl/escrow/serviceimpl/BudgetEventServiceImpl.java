package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.BudgetEventDTO;
import com.cdl.escrow.entity.BudgetEvent;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.BudgetEventMapper;
import com.cdl.escrow.repository.BudgetEventRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.BudgetEventService;
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
public class BudgetEventServiceImpl implements BudgetEventService {
    private final BudgetEventRepository repository;

    private final BudgetEventMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    public Page<BudgetEventDTO> getAllBudgetEvent(Pageable pageable) {
        log.debug("Fetching all Budget Event, page: {}", pageable.getPageNumber());
        Page<BudgetEvent> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<BudgetEventDTO> getBudgetEventById(Long id) {
        log.debug("Fetching budget Event with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public BudgetEventDTO saveBudgetEvent(BudgetEventDTO budgetEventDTO) {
        log.info("Saving new budget event");

        BudgetEvent entity = mapper.toEntity(budgetEventDTO);

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);*/

        BudgetEvent saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public BudgetEventDTO updateBudgetEvent(Long id, BudgetEventDTO budgetEventDTO) {
        log.info("Updating Budget Event with ID: {}", id);

        BudgetEvent existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Budget not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        BudgetEvent toUpdate = mapper.toEntity(budgetEventDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

       /* // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);*/
        BudgetEvent updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteBudgetEventById(Long id) {
        log.info("Deleting budget Event with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Budget Event not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public void finalizeBudgetEvent(Long moduleId, TaskStatus status) {

        BudgetEvent budget =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Budget Event not found: " + moduleId));

        //budget.setTaskStatus(status);
        repository.save(budget);
    }

    @Override
    public boolean softBudgetEventServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            // entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
