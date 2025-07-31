package com.bigcenter.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "classes", indexes = {
        @Index(name = "idx_classes_tenant", columnList = "tenant_id")
})
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "class_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "room")
    private String room;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", unique = true)
    private Subject subject;

    @OneToMany(mappedBy = "classField", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ClassesStudent> classesStudents = new ArrayList<>();

    // Override equals và hashCode để tránh duplicate issues
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}