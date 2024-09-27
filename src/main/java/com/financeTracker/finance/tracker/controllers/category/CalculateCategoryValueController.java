package com.financeTracker.finance.tracker.controllers.category;

import com.financeTracker.finance.tracker.dto.category.CategoryResponseDto;
import com.financeTracker.finance.tracker.dto.category.CategorySummaryResponseDto;
import com.financeTracker.finance.tracker.useCases.category.CalculateCategoryValueUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CalculateCategoryValueController {

    private final CalculateCategoryValueUseCase calculateCategoryValueUseCase;

    @GetMapping("/get")
    public ResponseEntity<CategorySummaryResponseDto> calculateValue() {
        CategorySummaryResponseDto response = this.calculateCategoryValueUseCase.execute();
        return ResponseEntity.ok(response);
    }
}
