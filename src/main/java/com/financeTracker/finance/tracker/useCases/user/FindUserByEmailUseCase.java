package com.financeTracker.finance.tracker.useCases.user;

import com.financeTracker.finance.tracker.dto.user.UserResponseDto;
import com.financeTracker.finance.tracker.entities.UserEntity;
import com.financeTracker.finance.tracker.exceptions.NotFoundException;
import com.financeTracker.finance.tracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByEmailUseCase {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseDto execute(String email){
        UserEntity user = this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        return this.modelMapper.map(user,UserResponseDto.class);
    }
}
