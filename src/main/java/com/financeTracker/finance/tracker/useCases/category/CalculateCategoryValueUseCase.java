package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.dto.category.CategoryResponseDto;
import com.financeTracker.finance.tracker.dto.category.CategorySummaryResponseDto;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.Expense;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateCategoryValueUseCase {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CategorySummaryResponseDto execute(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
        List<Category> categories = this.categoryRepository.findByUser(user);

        Double totalValue = categories.stream()
                .flatMap(category -> category.getExpenses().stream())
                .mapToDouble(Expense::getValue)
                .sum();

        if (totalValue == 0) {
            for (Category category : categories) {
                CategoryResponseDto categoryResponseDto = this.modelMapper.map(category, CategoryResponseDto.class);
                categoryResponseDto.setPercentage(0.0);
                categoryResponseDto.setValue(0.0);
                categoryResponseDtos.add(categoryResponseDto);
            }
            return new CategorySummaryResponseDto(categoryResponseDtos, totalValue);
        }

        for (Category category : categories) {
            Double categoryTotal = category.getExpenses().stream()
                    .mapToDouble(Expense::getValue)
                    .sum();

            Double percentage = (categoryTotal / totalValue) * 100;

            CategoryResponseDto categoryResponseDto = this.modelMapper.map(category, CategoryResponseDto.class);
            categoryResponseDto.setPercentage(percentage);
            categoryResponseDto.setValue(categoryTotal);
            categoryResponseDtos.add(categoryResponseDto);
        }

        return new CategorySummaryResponseDto(categoryResponseDtos, totalValue);
    }
}

