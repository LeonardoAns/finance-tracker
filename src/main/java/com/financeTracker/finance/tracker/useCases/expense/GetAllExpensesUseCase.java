package com.financeTracker.finance.tracker.useCases.expense;

import com.financeTracker.finance.tracker.dto.expense.ExpenseResponseDto;
import com.financeTracker.finance.tracker.entities.Expense;
import com.financeTracker.finance.tracker.repositories.ExpenseRepository;
import com.financeTracker.finance.tracker.useCases.auth.AuthenticatedUserUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllExpensesUseCase {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserUseCase authenticatedUserUseCase;

    public List<ExpenseResponseDto> execute() {
        Long userId = this.authenticatedUserUseCase.getAuthenticatedUserId();

        List<Expense> expenses = this.expenseRepository.findByUserId(userId);
        return expenses.stream()
                .map(expense -> this.modelMapper.map(expense, ExpenseResponseDto.class))
                .toList();
    }
}

