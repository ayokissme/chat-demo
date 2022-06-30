package com.demo.chat.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @JsonView(Views.NoPassword.class)
    private UUID id;

    @JsonView(Views.NoPassword.class)
    private String username;

    @JsonView(Views.WithPassword.class)
    private String password;

    @Column(name = "photo")
    @JsonView(Views.NoPassword.class)
    private String photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("USER"));
        return null;
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
        return true;
    }
}
