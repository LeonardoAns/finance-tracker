package com.financeTracker.finance.tracker.controllers.category;

import com.financeTracker.finance.tracker.useCases.category.DeleteCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class DeleteCategoryController {

    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @DeleteMapping("/delete/{userId}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long userId,
                                       @PathVariable Long id) {
        this.deleteCategoryUseCase.execute(userId, id);
        return ResponseEntity.noContent().build();
    }
}

