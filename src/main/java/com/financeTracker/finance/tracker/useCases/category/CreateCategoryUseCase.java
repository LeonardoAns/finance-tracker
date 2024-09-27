package com.financeTracker.finance.tracker.useCases.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.InvalidRequestException;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.CategoryRepository;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import com.financeTracker.finance.tracker.useCases.auth.AuthenticatedUserUseCase;
import com.financeTracker.finance.tracker.useCases.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserUseCase authenticatedUserUseCase;

    public void execute(CategoryRequestDto categoryRequestDto) {
        Long userId = authenticatedUserUseCase.getAuthenticatedUserId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        boolean exists = categoryRepository.existsByNameAndUser(categoryRequestDto.getName(), user);
        if (exists) {
            throw new InvalidRequestException("Category name must be unique");
        }

        Category category = modelMapper.map(categoryRequestDto, Category.class);
        category.setUser(user);
        categoryRepository.save(category);
    }
}
