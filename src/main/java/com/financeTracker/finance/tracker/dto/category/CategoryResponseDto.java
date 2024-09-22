package com.financeTracker.finance.tracker.dto.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.financeTracker.finance.tracker.dto.expense.ExpenseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

    private Long id;
    private String name;

    @JsonManagedReference
    private List<ExpenseResponseDto> expenses;
    private Double percentage;
    private Double value;
}
