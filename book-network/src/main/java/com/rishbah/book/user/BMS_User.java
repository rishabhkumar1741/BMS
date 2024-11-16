package com.rishbah.book.user;

import com.rishbah.book.role.BMS_Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BMS_User")
@EntityListeners(AuditingEntityListener.class)
public class BMS_User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime dateOfBirth;
    @Column(unique = true)
    private String email;
    private boolean enabled;
    private boolean accountLocked;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BMS_Role> roles;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime lastmodifiedDate;

    @Override
    public String getName() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    private String fullName()
    {
        return this.firstName + " " + this.lastName;
    }
}
