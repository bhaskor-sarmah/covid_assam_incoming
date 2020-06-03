package com.bohniman.incomingportal.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bohniman.incomingportal.model.MasterDistrict;
import com.bohniman.incomingportal.model.User;
import com.bohniman.incomingportal.model.UserScope;
import com.bohniman.incomingportal.repository.MasterDistrictRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * MyUserDetails
 */
public class MyUserDetails implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String mobileNo;
    private String email;
    private boolean status;
    private List<GrantedAuthority> authorities;
    private String userScope;
    private String userScopeName;

    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.mobileNo = user.getMobileNo();
        this.email = user.getEmailId();
        this.status = user.getStatus();
        this.authorities = user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
        if (!Objects.equals(user.getUserScope(), null)) {
            this.userScope = user.getUserScope().getDistrict().getDistrictCode();
            this.userScopeName = user.getUserScope().getDistrict().getDistrictName();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public String getUserScope() {
        return userScope;
    }

    public String getUserScopeName() {
        return userScopeName;
    }

    public void setUserScopeName(String userScopeName) {
        this.userScopeName = userScopeName;
    }

}