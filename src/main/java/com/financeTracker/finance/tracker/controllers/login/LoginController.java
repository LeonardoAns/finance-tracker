package com.financeTracker.finance.tracker.controllers.login;

import com.financeTracker.finance.tracker.dto.login.LoginRequestDto;
import com.financeTracker.finance.tracker.dto.login.LoginResponseDto;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.InvalidRequestException;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import com.financeTracker.finance.tracker.useCases.jwt.GenerateTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final GenerateTokenUseCase generateTokenUseCase;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        UserEntity user = this.userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new RuntimeException("user not found"));
        if(user.getVerificationCode() != null){
            throw new InvalidRequestException("Para fazer login é necessario confirmar o email");
        }
        if(passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            String token = this.generateTokenUseCase.generateToken(user);
            LoginResponseDto response = new LoginResponseDto(token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }
}
