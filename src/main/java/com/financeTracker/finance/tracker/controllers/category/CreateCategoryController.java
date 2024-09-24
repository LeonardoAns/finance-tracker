package com.financeTracker.finance.tracker.controllers.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.useCases.category.CreateCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CreateCategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping("/create/{userId}")
    public ResponseEntity<Void> createCategory(@PathVariable Long userId,
                                               @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        this.createCategoryUseCase.execute(userId, categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

