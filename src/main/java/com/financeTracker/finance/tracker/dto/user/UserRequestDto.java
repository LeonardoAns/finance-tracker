package com.financeTracker.finance.tracker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull(message = "campo nome não pode estar nulo")
    @NotBlank(message = "campo nome não pode estar vazio")
    private String name;

    @Email(message = "insira um email válido")
    private String email;

    @NotNull(message = "campo senha não pode estar nulo")
    @NotBlank(message = "campo senha não pode estar vazio")
    private String password;
}
