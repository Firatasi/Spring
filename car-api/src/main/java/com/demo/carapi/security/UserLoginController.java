package com.demo.carapi.security;

import com.demo.carapi.dto.LoginDto;
import com.demo.carapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.saml2.Saml2LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserLoginController {

    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MyAuhtenticationProvider myAuhtenticationProvider;
    private final JwtService jwtService;
    private final UserService userService;

    public UserLoginController(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder, MyAuhtenticationProvider myAuhtenticationProvider, JwtService jwtService, UserService userService) {
        this.myUserDetailsService = myUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.myAuhtenticationProvider = myAuhtenticationProvider;
        this.jwtService = jwtService;
        this.userService = userService;
    }

//    @PostMapping("/login") security kısmı ilk videolar
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
//
//        try {
//
//            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDto.getUsername());
//            //şifreye bakalım
//            boolean matches = passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword());
//            if (matches){
//                String token = jwtService.generateToken(userDetails);//jwtden sonra
//                return ResponseEntity.ok(Map.of(
//                        "işlem", "Giriş Başarılı",
//                        "path", request.getRequestURI(),
//                        "status code", HttpStatus.OK.value(),
//                        "token", token));
//            }else {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("şifre hatalı!");
//            }
//
//        } catch (UsernameNotFoundException e) {
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("kayıt bulunamadı");
//
//        } catch (Exception e) {
//
//            return ResponseEntity.badRequest().build();
//
//        }
//
//    }

    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginRequest) {
        try{
            Authentication authentication = myAuhtenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            //hem refreshtoken hem access(jwt)
            TokenPair tokenPair = jwtService.generateTokenPair(userDetails);

            return ResponseEntity.ok(Map.of(
                    "sonuc","Login Başarılı!",
                    "accessToken", tokenPair.getAccessToken(),
                    "refreshToken", tokenPair.getRefreshToken(),
                    "username",userDetails.getUsername()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Geçersiz kullanıcı adı veya şifre");
        }
    }


    //kendi myauhtenticationproviderımızı yazdıktan sonra
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshToken request) { //frontendden yollanır genellike görmek için burda yazıyoz
       try {
           String username = jwtService.refreshAccessToken(request.getToken());
           UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

            //yeni token çifti oluştur
             TokenPair newTokenPair = jwtService.generateTokenPair(userDetails);
             return ResponseEntity.ok(Map.of(
                     "sonuç", "Token Yenilendi",
                     "AccessToken", newTokenPair.getAccessToken(),
                     "RefreshToken", newTokenPair.getRefreshToken()
             ));
       } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }

    public ResponseEntity<?> logout(@RequestBody TokenPair tokenPair) {
        try {
            String username = jwtService.getUsernameFromToken(tokenPair.getAccessToken());
            jwtService.revokeAllRefreshTokens(username);
            return ResponseEntity.ok("Logout başarılı!");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout işlemi başarısız");
        }
    }


}
