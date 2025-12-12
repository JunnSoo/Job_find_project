package com.project.codinviec.repository;

import com.project.codinviec.entity.StatusSpecialCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusSpecialCompanyRepository extends JpaRepository<StatusSpecialCompany, Integer> {
    List<StatusSpecialCompany> findByIdCompany_Id(String idCompanyId);
    List<StatusSpecialCompany> findByIdStatusSpecial_Id(Integer id);
}
