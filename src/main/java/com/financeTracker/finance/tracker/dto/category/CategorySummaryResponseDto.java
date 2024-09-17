package com.financeTracker.finance.tracker.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySummaryResponseDto {
    private List<CategoryResponseDto> categories;
    private Double totalValue;
}
