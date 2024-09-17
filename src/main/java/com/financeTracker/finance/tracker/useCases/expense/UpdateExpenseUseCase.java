package com.financeTracker.finance.tracker.useCases.expense;

import com.financeTracker.finance.tracker.dto.expense.ExpenseRequestDto;
import com.financeTracker.finance.tracker.entities.Expense;
import com.financeTracker.finance.tracker.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public void execute(Long id, ExpenseRequestDto expenseRequestDto){
        Expense expense = this.expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("expense not found"));
        expense.setDescription(expenseRequestDto.getDescription());
        expense.setValue(expenseRequestDto.getValue());
        expense.setPaymentMethod(expenseRequestDto.getPaymentMethod());
        expense.setCategory(expenseRequestDto.getCategory());
    }
}
