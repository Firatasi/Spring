package com.demo.blogapi.users;


import com.demo.blogapi.security.AuthenticatedUser;
import com.demo.blogapi.users.dto.UpdateMeRequest;
import com.demo.blogapi.users.dto.UserMeResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserMeResponse me(@AuthenticationPrincipal AuthenticatedUser au) {
        return userService.me(au);
    }

    @PutMapping("/me")
    public UserMeResponse updateMe(@AuthenticationPrincipal AuthenticatedUser au,
                                   @Valid @RequestBody UpdateMeRequest req) {
        return userService.updateMe(au, req);
    }
}

