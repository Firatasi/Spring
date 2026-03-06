package com.demo.carapi.security;

import com.demo.carapi.dto.LoginDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MyAuhtenticationProvider myAuhtenticationProvider;

    public UserLoginController(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder, MyAuhtenticationProvider myAuhtenticationProvider) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.myAuhtenticationProvider = myAuhtenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {

        try {

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDto.getUsername());
            //şifreye bakalım
            boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
            if (matches){
                return ResponseEntity.ok("giriş başarılı");
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("şifre hatalı!");
            }

        } catch (UsernameNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("kayıt bulunamadı");

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();

        }

    }

    //kendi myauhtenticationproviderımızı yazdıktan sonra
    @PostMapping("/login2")
    public ResponseEntity<?> login2(@Valid @RequestBody LoginDto loginDto) {
       String username = loginDto.getUsername();
       String password = loginDto.getPassword();

        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = myAuhtenticationProvider.authenticate(authentication);
        return ResponseEntity.ok(authenticate);

    }


}
