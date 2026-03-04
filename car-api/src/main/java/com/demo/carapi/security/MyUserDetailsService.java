package com.demo.carapi.security;

import com.demo.carapi.entity.User;
import com.demo.carapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//kullanıcı adına göre kullanıcıyı getirir veritabanı işlemleri olduğu için repositoryi çağırıyoruz

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//geriye userdetails dönmesi lazım bunuda myuserdetails sınıfında yapıyoruz
        MyUserDetails myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }
}
