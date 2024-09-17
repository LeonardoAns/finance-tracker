package com.financeTracker.finance.tracker.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String name;
    private String email;

    @JsonFormat(pattern = "MM/dd/yyyy/ HH:mm:ss")
    private LocalDateTime createdAt;
}
