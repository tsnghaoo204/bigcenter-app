package com.bigcenter.app.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_tenant", columnList = "tenant_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "users_username_key", columnNames = {"username"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @Column(name = "cognito_sub", nullable = false, unique = true, length = 36)
    private String cognitoSub; // lấy từ token "sub" của Cognito

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "phone_number", length = 12)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @ColumnDefault("true")
    @Column(name = "enable")
    private Boolean enable;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_at")
    private Instant createAt;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

}