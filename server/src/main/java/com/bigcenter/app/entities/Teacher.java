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
@Table(name = "teachers", indexes = {
        @Index(name = "idx_teachers_user", columnList = "user_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "teachers_user_id_key", columnNames = {"user_id"})
})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "teacher_id", nullable = false)
    private UUID id;

    @Column(name = "specialization", length = 50)
    private String specialization;

    @Column(name = "phone", length = 20)
    private String phone;


    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_at")
    private Instant updateAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}