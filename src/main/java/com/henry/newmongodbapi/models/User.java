package com.henry.newmongodbapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Setter
@Document(collection = "users")
public class User implements UserDetails {
    @Getter
    @MongoId(FieldType.OBJECT_ID)
    private String _id;

    private String username;

    private String password;

    @Getter
    private Short status;

    @Getter
    private Role role;

    @Getter
    private Short isAccountExpired;

    @Getter
    private Short isAccountLocked;

    @Getter
    private Short isCredentialsExpired;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isAccountExpired == 0;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return isAccountLocked == 0;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsExpired == 0;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }
}