package com.financeTracker.finance.tracker.repositories;

import com.financeTracker.finance.tracker.entities.Category;
import com.financeTracker.finance.tracker.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    Optional<Category> findByIdAndUser(Long id, UserEntity user);
    List<Category> findByUser(UserEntity user);
    boolean existsByNameAndUser(String name, UserEntity user);
}
