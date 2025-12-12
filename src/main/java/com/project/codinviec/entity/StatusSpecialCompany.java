package com.project.codinviec.entity;

import com.project.codinviec.entity.key.StatusSpecialCompanyId;
import com.project.codinviec.entity.auth.Company;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "status_special_company", schema = "codinviec_db")
public class StatusSpecialCompany {
    @EmbeddedId
    private StatusSpecialCompanyId id;

    @MapsId("idCompany")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_company", nullable = false)
    private Company idCompany;

    @MapsId("idStatusSpecial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_status_special", nullable = false)
    private StatusSpecial idStatusSpecial;

}