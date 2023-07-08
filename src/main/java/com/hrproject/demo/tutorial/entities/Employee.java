package com.hrproject.demo.tutorial.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrproject.demo.tutorial.entities.entityUtil.Social;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
@Data
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends BaseEntity implements UserDetails {
    private String firstName;
    private String nickName;
    private String phoneNo;
    @Indexed(unique = true)
    private String email;
    private String lastName;
    private String password;
    private Role role;
    private String department;
    private LocalDate startDate;
    private String workLocation;
    private String contractType;
    private Social social;
    private String imageUrl;
    private String resumeUrl;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String address;
    private NextOfKin nextOfKin;
    private String nationality;
    private String maritalStatus;
    private String position;
    private String reportTo;
    private boolean loggedIn;
    private String teamLeader;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
