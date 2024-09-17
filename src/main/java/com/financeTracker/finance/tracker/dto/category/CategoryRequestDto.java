package com.financeTracker.finance.tracker.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDto {

    @NotNull(message = "campo nome não pode estar nulo")
    @NotBlank(message = "campo nome não pode estar vazio")
    private String name;
}
