package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.exceptions.AlreadyExistsException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public void execute(CategoryRequestDto categoryRequestDto){
        Category category = this.modelMapper.map(categoryRequestDto,Category.class);
        if(this.categoryRepository.existsByName(category.getName())){
            throw new AlreadyExistsException("Category Already Exists");
        }
        this.categoryRepository.save(category);
    }
}
