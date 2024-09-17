package com.financeTracker.finance.tracker.useCases.expense;

import com.financeTracker.finance.tracker.entities.Expense;
import com.financeTracker.finance.tracker.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public void execute(Long id){
        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("expense not found"));
        this.expenseRepository.delete(expense);
    }
}
