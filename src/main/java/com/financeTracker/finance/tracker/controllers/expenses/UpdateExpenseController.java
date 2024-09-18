package com.financeTracker.finance.tracker.controllers.expenses;

import com.financeTracker.finance.tracker.dto.expense.ExpenseRequestDto;
import com.financeTracker.finance.tracker.useCases.expense.UpdateExpenseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class UpdateExpenseController {

    private final UpdateExpenseUseCase expenseUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ExpenseRequestDto expenseRequestDto){
        this.expenseUseCase.execute(id,expenseRequestDto);
        return ResponseEntity.ok().build();
    }
}
