package com.drj.bankapp.AuthenticationModels;

import com.drj.bankapp.model.Bankuser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class CustomUserDetails implements UserDetails {


    private String username;
    private String password;

    @JsonIgnore
    private Set<String> roles;

    public CustomUserDetails(Bankuser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.roles = user.getBankUserAuthorities()
                .stream()
                .map(bankUserAuthority ->
                        bankUserAuthority.getBankAuthority())
                .collect(Collectors.toSet());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role ->new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
