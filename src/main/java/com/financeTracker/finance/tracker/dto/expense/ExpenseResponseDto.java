package com.financeTracker.finance.tracker.dto.expense;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponseDto {

    private Long id;
    private Double value;
    private PaymentMethod paymentMethod;

    @JsonBackReference
    private Category category;
}
