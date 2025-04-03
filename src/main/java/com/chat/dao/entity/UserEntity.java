package com.chat.dao.entity;

import com.chat.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@Builder
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    UserRole userRole;

    LocalDateTime registerDate = LocalDateTime.now();
    LocalDateTime lastUpdateDate = LocalDateTime.now();
    @OneToMany(mappedBy = "user", cascade = ALL)
    Set<TokenEntity> tokens;

    @OneToMany(mappedBy = "user", cascade = ALL)
    Set<AccountEntity> accounts;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
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
        return true;
    }



}
