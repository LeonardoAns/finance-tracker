package com.financeTracker.finance.tracker.dto.expense;

import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequestDto {

    private String description;

    @NotNull(message = "campo valor não pode estar nulo")
    private Double value;

    @NotNull(message = "campo metodo de pagamento não pode estar nulo")
    private PaymentMethod paymentMethod;

    @NotNull(message = "selecione uma categoria")
    private Category category;
}
