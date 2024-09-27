package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import com.financeTracker.finance.tracker.useCases.auth.AuthenticatedUserUseCase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserUseCase authenticatedUserUseCase;

    public void execute(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Long userId = this.authenticatedUserUseCase.getAuthenticatedUserId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Category category = categoryRepository.findByIdAndUser(categoryId, user)
                .orElseThrow(() -> new NotFoundException("Category not found for this user"));

        modelMapper.map(categoryRequestDto, category);
        categoryRepository.save(category);
    }
}
