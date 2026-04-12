package com.firat.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.firat.example.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);//kullanıcı var mı
}
