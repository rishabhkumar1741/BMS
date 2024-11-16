package com.rishbah.book.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BMS_Token {

    @Id
    @GeneratedValue
    private Integer tokenid;
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "BMSUserId", nullable = false)
    private BMS_User user;
}
