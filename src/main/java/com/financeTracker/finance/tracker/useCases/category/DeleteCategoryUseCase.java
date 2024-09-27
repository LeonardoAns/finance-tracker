package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import com.financeTracker.finance.tracker.useCases.auth.AuthenticatedUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AuthenticatedUserUseCase authenticatedUserUseCase;

    public void execute(Long categoryId) {
        Long userId = authenticatedUserUseCase.getAuthenticatedUserId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Category category = categoryRepository.findByIdAndUser(categoryId, user)
                .orElseThrow(() -> new NotFoundException("Category not found for this user"));

        categoryRepository.delete(category);
    }
}
