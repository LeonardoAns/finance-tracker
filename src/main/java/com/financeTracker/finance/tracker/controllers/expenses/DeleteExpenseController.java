package com.financeTracker.finance.tracker.controllers.expenses;

import com.financeTracker.finance.tracker.useCases.expense.DeleteExpenseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class DeleteExpenseController {

    private final DeleteExpenseUseCase deleteExpenseUseCase;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.deleteExpenseUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
