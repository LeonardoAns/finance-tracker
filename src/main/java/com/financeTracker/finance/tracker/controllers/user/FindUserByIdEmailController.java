package com.financeTracker.finance.tracker.controllers.user;

import com.financeTracker.finance.tracker.dto.user.UserResponseDto;
import com.financeTracker.finance.tracker.useCases.user.FindUserByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class FindUserByIdEmailController {

    private final FindUserByEmailUseCase findUserByEmailUseCase;

    @GetMapping("/get/{email}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String email){
        UserResponseDto response = findUserByEmailUseCase.execute(email);
        return ResponseEntity.ok(response);
    }
}
