package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void execute(Long id){
        Category category = this.categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        this.categoryRepository.delete(category);
    }
}
