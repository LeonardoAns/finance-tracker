package com.financeTracker.finance.tracker.useCases.user;

import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserUseCase {

    private final UserRepository userRepository;


    public boolean execute(String verificationCode) {
        UserEntity user = this.userRepository.findByVerificationCode(verificationCode).orElseThrow(() -> new NotFoundException("Verification Code Not Found"));

        if(user == null || user.isEnabled()){
            return false;
        }
        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepository.save(user);

        return true;

    }
}
