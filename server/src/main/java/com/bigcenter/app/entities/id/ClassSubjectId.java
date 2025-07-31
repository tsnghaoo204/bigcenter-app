package com.bigcenter.app.entities.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class ClassSubjectId implements Serializable {
    private static final long serialVersionUID = 2946482487379371904L;
    @NotNull
    @Column(name = "class_id", nullable = false)
    private UUID classId;

    @NotNull
    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ClassSubjectId entity = (ClassSubjectId) o;
        return Objects.equals(this.classId, entity.classId) &&
                Objects.equals(this.subjectId, entity.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, subjectId);
    }

}