package com.financeTracker.finance.tracker.controllers.category;

import com.financeTracker.finance.tracker.dto.category.CategoryRequestDto;
import com.financeTracker.finance.tracker.useCases.category.UpdateCategoryUseCase;
import com.financeTracker.finance.tracker.useCases.expense.UpdateExpenseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class UpdateCategoryController {

    private final UpdateCategoryUseCase updateCategoryUseCase;

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CategoryRequestDto categoryRequestDto){
        this.updateCategoryUseCase.execute(id, categoryRequestDto);
        return ResponseEntity.ok().build();
    }
}
