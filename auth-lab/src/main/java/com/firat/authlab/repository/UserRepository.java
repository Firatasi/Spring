package com.firat.authlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.firat.authlab.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);//aynı username önceden kayıtlı mı?
    boolean existsByEmail(String email);// aynı mail önceden kayıtlı mı?
}
