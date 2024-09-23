package com.financeTracker.finance.tracker.repositories;

import com.financeTracker.finance.tracker.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByVerificationCode(String verificationCode);
    boolean existsByEmail(String email);

}
