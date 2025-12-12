package com.project.codinviec.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class StatusSpecialCompanyId implements Serializable {
    private static final long serialVersionUID = -2795812525946905163L;
    @Size(max = 36)
    @NotNull
    @Column(name = "id_company", nullable = false, length = 36)
    private String idCompany;

    @NotNull
    @Column(name = "id_status_special", nullable = false)
    private Integer idStatusSpecial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StatusSpecialCompanyId entity = (StatusSpecialCompanyId) o;
        return Objects.equals(this.idCompany, entity.idCompany) &&
                Objects.equals(this.idStatusSpecial, entity.idStatusSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompany, idStatusSpecial);
    }

}