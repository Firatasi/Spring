package com.demo.carapi.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationController { // giriş yapmış kullanıcıların bilgilerini görüntülemek için yazarız

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@AuthenticationPrincipal MyUserDetails myUserDetails) {

        String username = myUserDetails.getUsername();
        GrantedAuthority grantedAuthority = myUserDetails.getAuthorities().stream().findFirst().orElse(null);
        return ResponseEntity.ok(
                Map.of(
                        "username", username,
                        "role", grantedAuthority
                )
        );
    }

    @GetMapping("/profile2")
    public ResponseEntity<?> profile2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(
                Map.of(
                        "username", userDetails.getUsername(),
                        "role", userDetails.getAuthorities()
                )
        );

    }

    @GetMapping("/profile5")
    public ResponseEntity<?> profile5(@CurrentSecurityContext SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        return ResponseEntity.ok(
                Map.of(
                        "username", authentication.getName(),
                        "role", authentication.getAuthorities()
                )
        );
    }


//    @GetMapping("/isAdmin")
//    public boolean isAdmin(@CurrentSecurityContext(expression = "authentication.authorities.?[authority=='ROLE_ADMIN'].isAdmin")){
//        return isAdmin();
//    }
//
//
//    @GetMapping("/email")
//    public String isAdmin(@CurrentSecurityContext(expression = "authentication.principal.email") String email) {
//        return email;
//    }

}
