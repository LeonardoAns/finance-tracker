package com.financeTracker.finance.tracker.useCases.user;

import com.financeTracker.finance.tracker.dto.user.UserRequestDto;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.entities.enums.Roles;
import com.financeTracker.finance.tracker.exceptions.AlreadyExistsException;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import com.financeTracker.finance.tracker.useCases.mail.SendVerificationEmail;
import com.financeTracker.finance.tracker.utils.GenerateVerificationCode;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final SendVerificationEmail sendVerificationEmail;

    public void execute(UserRequestDto userRequestDto) throws MessagingException, UnsupportedEncodingException {
        if(this.userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new AlreadyExistsException("Email already exists");
        }
        UserEntity user = this.modelMapper.map(userRequestDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String randomCode = GenerateVerificationCode.generateRandomCode();
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        user.setRoles(List.of(Roles.USER));
        this.userRepository.save(user);
        this.sendVerificationEmail.execute(user);
    }
}
