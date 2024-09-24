package com.financeTracker.finance.tracker.useCases.expense;

import com.financeTracker.finance.tracker.dto.expense.ExpenseRequestDto;
import com.financeTracker.finance.tracker.entities.Expense;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import com.financeTracker.finance.tracker.repositories.ExpenseRepository;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository; // Adicionando UserRepository

    public void execute(ExpenseRequestDto expenseRequestDto, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Expense expense = this.modelMapper.map(expenseRequestDto, Expense.class);
        expense.setUser(user);
        this.expenseRepository.save(expense);
    }
}
