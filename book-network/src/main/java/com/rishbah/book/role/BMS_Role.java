package com.rishbah.book.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rishbah.book.user.BMS_User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BMS_Role")
@EntityListeners(AuditingEntityListener.class)
public class BMS_Role {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<BMS_User> users;

    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime lastmodifiedDate;
}
