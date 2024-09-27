package com.financeTracker.finance.tracker.controllers.expenses;

import com.financeTracker.finance.tracker.dto.expense.ExpenseResponseDto;
import com.financeTracker.finance.tracker.useCases.expense.GetAllExpensesUseCase;
import jakarta.validation.GroupSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class GetAllExpensesController {

    private final GetAllExpensesUseCase getAllExpensesUseCase;

    @GetMapping("/user/{userId}/get")
    public ResponseEntity<List<ExpenseResponseDto>> getExpenses() {
        List<ExpenseResponseDto> response = this.getAllExpensesUseCase.execute();
        return ResponseEntity.ok(response);
    }
}

