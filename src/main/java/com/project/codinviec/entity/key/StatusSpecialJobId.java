package com.project.codinviec.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StatusSpecialJobId implements Serializable {
    private static final long serialVersionUID = -8677803785609456229L;
    @NotNull
    @Column(name = "id_job", nullable = false)
    private Integer idJob;

    @NotNull
    @Column(name = "id_status_special", nullable = false)
    private Integer idStatusSpecial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatusSpecialJobId entity = (StatusSpecialJobId) o;
        return Objects.equals(this.idJob, entity.idJob) &&
                Objects.equals(this.idStatusSpecial, entity.idStatusSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJob, idStatusSpecial);
    }

}