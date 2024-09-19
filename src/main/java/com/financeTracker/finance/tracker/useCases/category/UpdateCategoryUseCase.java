package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void execute(Long id, CategoryRequestDto categoryRequestDto){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found"));
        category.setName(categoryRequestDto.getName());
        this.categoryRepository.save(category);
    }

}
