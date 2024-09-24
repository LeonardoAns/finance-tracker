package com.financeTracker.finance.tracker.controllers.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.useCases.category.UpdateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class UpdateCategoryController {

    private final UpdateCategoryUseCase updateCategoryUseCase;

    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Void> update(@PathVariable Long userId,
                                       @PathVariable Long id,
                                       @RequestBody CategoryRequestDto categoryRequestDto) {
        this.updateCategoryUseCase.execute(userId, id, categoryRequestDto);
        return ResponseEntity.ok().build();
    }
}

