package com.smartcane.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name = "users")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=190)
    private String email;

    @Column(nullable=false, length=100)
    private String nickname;

    private LocalDate birthDate;

    @Column(nullable=false, length=20)
    private String role;   // USER|ADMIN
    @Column(nullable=false, length=20)
    private String status; // ACTIVE|SUSPENDED|DELETED

    @Column(nullable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;

    @PrePersist void prePersist() {
        var now = LocalDateTime.now();
        createdAt = now; updatedAt = now;
        if (role == null) role = "USER";
        if (status == null) status = "ACTIVE";
    }
    @PreUpdate void preUpdate() { updatedAt = LocalDateTime.now(); }
}
