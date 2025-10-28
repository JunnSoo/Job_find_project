package com.project.it_job.service.imp.auth;

import com.project.it_job.dto.auth.CompanyDTO;
import com.project.it_job.entity.CompanySize;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.auth.CompanyMapper;
import com.project.it_job.repository.CompanySizeRepository;
import com.project.it_job.repository.auth.CompanyRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUpdateCompanyRequest;
import com.project.it_job.service.auth.CompanyService;
import com.project.it_job.specification.auth.CompanySpecification;
import com.project.it_job.util.PageCustomHelpper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final CompanySpecification companySpecification;
    private final CompanySizeRepository companySizeRepository;


    @Override
    public List<CompanyDTO> getAllCompany() {
        return companyRepository.findAll().stream().map(company -> companyMapper.companyToCompanyDTO(company)).toList();
    }

    @Override
    public Page<CompanyDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom) {
//        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);
//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<Company> spec = Specification.allOf(companySpecification.searchByName(pageRequestValidate.getKeyword()));
        return companyRepository.findAll(spec, pageable).map(company -> companyMapper.companyToCompanyDTO(company));
    }

    @Override
    public CompanyDTO getCompanyById(String idCompany) {
        Company company = companyRepository.findById(idCompany).orElseThrow(() -> new  NotFoundIdExceptionHandler("Không tìm thấy id company!"));
        return  companyMapper.companyToCompanyDTO(company);
    }


    @Override
    @Transactional
    public CompanyDTO saveCompany(SaveUpdateCompanyRequest saveUpdateCompanyRequest) {
            CompanySize companySize = companySizeRepository.findById(saveUpdateCompanyRequest.getCompanySizeId())
                    .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));
            Company company = companyMapper.saveCompanyMapper(companySize, saveUpdateCompanyRequest);
            return  companyMapper.companyToCompanyDTO(companyRepository.save(company));
    }

    @Override
    @Transactional
    public CompanyDTO updateCompany(String idCompany, SaveUpdateCompanyRequest saveUpdateCompanyRequest) {
            CompanySize companySize = companySizeRepository.findById(saveUpdateCompanyRequest.getCompanySizeId())
                    .orElseThrow(()->new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));

            Company company = companyRepository.findById(idCompany).orElseThrow(() -> new  NotFoundIdExceptionHandler("Không tìm thấy id company!"));

            Company mappedCompany = companyMapper.updateCompanyMapper(idCompany,companySize,saveUpdateCompanyRequest);
            mappedCompany.setCreatedDate(company.getCreatedDate());
            return   companyMapper.companyToCompanyDTO(companyRepository.save(mappedCompany));
    }

    @Override
    public CompanyDTO deleteCompany(String idCompany) {
        Company company = companyRepository.findById(idCompany).orElseThrow(() -> new  NotFoundIdExceptionHandler("Không tìm thấy id company!"));
        companyRepository.delete(company);
        return  companyMapper.companyToCompanyDTO(company);
    }
}
