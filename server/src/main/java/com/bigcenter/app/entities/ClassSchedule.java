package com.bigcenter.app.entities;

import com.bigcenter.app.status.ClassStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "class_schedule", indexes = {
        @Index(name = "idx_schedule_tenant_date", columnList = "tenant_id, date")
})
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "class_schedule_id_gen")
    @SequenceGenerator(name = "class_schedule_id_gen", sequenceName = "class_schedule_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private Class classField;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClassStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

}