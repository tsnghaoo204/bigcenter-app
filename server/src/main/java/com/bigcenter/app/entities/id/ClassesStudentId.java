package com.bigcenter.app.entities.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ClassesStudentId implements Serializable {
    private static final long serialVersionUID = -7038382919701906214L;
    @Column(name = "class_id", nullable = false)
    private UUID classId;

    @Column(name = "student_id", nullable = false)
    private UUID studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClassesStudentId entity = (ClassesStudentId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.classId, entity.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, classId);
    }

}