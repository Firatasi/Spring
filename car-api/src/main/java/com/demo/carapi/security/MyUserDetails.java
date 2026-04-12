package com.demo.carapi.security;

import com.demo.carapi.entity.Role;
import com.demo.carapi.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    User user;

    public MyUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //rol kısmı

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public boolean isAccountNonExpired() {//kullanıcı hesabı zaman aşımına uğramış mı uğramamış mı
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//kullanıcının hesabı kilitli mi
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//Şifre kimlik süresi doldu mu örn 6ayda bir banka şif değiş
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {// kullanıcının hesabı aktif mi?
        return true;
    }

}
