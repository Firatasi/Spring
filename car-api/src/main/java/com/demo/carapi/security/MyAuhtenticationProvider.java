package com.demo.carapi.security;

import com.demo.carapi.entity.User;
import com.demo.carapi.repository.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MyAuhtenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public MyAuhtenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //kullanıcının yolladığı authenticationda username password var
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository.findByName(username)
                .orElseThrow(()-> new UsernameNotFoundException("Kullanıcı Adı Bulunamadı!"));

        if(passwordEncoder.matches(password, user.getPassword())) {
            List<GrantedAuthority> authorityList = new ArrayList<>(); // kullanıcı rolunude geri dönmemiz gerek
            authorityList.add(new SimpleGrantedAuthority(user.getRole().name()));
            return new UsernamePasswordAuthenticationToken(username, null, authorityList);
        }else {
            throw  new BadCredentialsException("Şifre Yanlış!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);//ezber böyle yazılır olduğu gibi implement ettiğimiz sınıftan kestik
    }
}
