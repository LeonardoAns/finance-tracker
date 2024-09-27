package com.financeTracker.finance.tracker.controllers.expenses;

import com.financeTracker.finance.tracker.dto.expense.ExpenseRequestDto;
import com.financeTracker.finance.tracker.useCases.expense.RegisterExpenseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class RegisterExpenseController {

    private final RegisterExpenseUseCase registerExpenseUseCase;

    @PostMapping("/register")
    public ResponseEntity<Void> registerExpense(@RequestBody @Valid ExpenseRequestDto expenseRequestDto) {
        this.registerExpenseUseCase.execute(expenseRequestDto);
        return ResponseEntity.ok().build();
    }
}