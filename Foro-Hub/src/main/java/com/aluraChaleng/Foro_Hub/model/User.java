package com.aluraChaleng.Foro_Hub.model;

import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoCreateUserToDatabase;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "user")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Code;
    private String username;
    private String email;
    private String password;

    @JoinColumn(name="type_profile", referencedColumnName="code")
    @OneToOne
    private Profile profile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public User(DtoCreateUserToDatabase dtoCreateUserToDatabase)
    {
        this.username = dtoCreateUserToDatabase.username();
        this.email = dtoCreateUserToDatabase.email();
        this.password = dtoCreateUserToDatabase.password();
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
