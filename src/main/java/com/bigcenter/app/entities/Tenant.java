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
@Table(name = "tenants", uniqueConstraints = {
        @UniqueConstraint(name = "tenants_code_key", columnNames = {"code"})
})
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "tenant_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "code", nullable = false, length = 8)
    private String code;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "logo_url", length = 100)
    private String logoUrl;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_at")
    private Instant createAt;

}