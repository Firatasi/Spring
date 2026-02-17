package com.demo.blogapi.users;
import com.demo.blogapi.exception.ApiException;
import com.demo.blogapi.security.AuthenticatedUser;
import com.demo.blogapi.users.dto.UpdateMeRequest;
import com.demo.blogapi.users.dto.UserMeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserMeResponse me(AuthenticatedUser au) {
        var u = userRepository.findById(au.id())
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "User not found"));
        return new UserMeResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole());
    }

    public UserMeResponse updateMe(AuthenticatedUser au, UpdateMeRequest req) {
        var u = userRepository.findById(au.id())
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "User not found"));

        if (req.fullName() != null && !req.fullName().isBlank()) {
            u.setFullName(req.fullName().trim());
        }
        if (req.password() != null && !req.password().isBlank()) {
            u.setPasswordHash(passwordEncoder.encode(req.password()));
        }

        userRepository.save(u);
        return new UserMeResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole());
    }
}
