package com.demo.carapi.security;

import com.demo.carapi.entity.Role;
import com.demo.carapi.entity.User;
import com.demo.carapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {

    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterController(MyUserDetailsService myUserDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String name,
                                         @RequestParam String password) {

    try {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(name);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı zaten kayıtlı! ");

    }catch (UsernameNotFoundException exception){
        User user = new User();
        user.setName(name);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(password));

        User save = userRepository.save(user);

        return ResponseEntity.ok().body("kayit yapildi");

    }catch (Exception exception){
        return ResponseEntity.badRequest().build();
    }

    }
}
