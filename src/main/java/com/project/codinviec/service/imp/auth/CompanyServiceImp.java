package com.project.codinviec.service.imp.auth;

import com.project.codinviec.dto.auth.CompanyDTO;
import com.project.codinviec.entity.CompanySize;
import com.project.codinviec.entity.auth.Company;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.auth.CompanyMapper;
import com.project.codinviec.repository.CompanySizeRepository;
import com.project.codinviec.repository.auth.CompanyRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.SaveUpdateCompanyRequest;
import com.project.codinviec.service.auth.CompanyService;
import com.project.codinviec.specification.auth.CompanySpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import org.springframework.transaction.annotation.Transactional;
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
        private final PageCustomHelper PageCustomHelper;
        private final CompanySpecification companySpecification;
        private final CompanySizeRepository companySizeRepository;

        @Override
        public List<CompanyDTO> getAllCompany() {
                return companyRepository.findAll().stream()
                                .map(companyMapper::companyToCompanyDTO)
                                .toList();
        }

        @Override
        public Page<CompanyDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom) {
                // Validate pageCustom
                PageRequestCustom pageRequestValidate = PageCustomHelper.validatePageCustom(pageRequestCustom);
                // Tạo page cho api
                Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,
                                pageRequestValidate.getPageSize());

                // Tạo search
                Specification<Company> spec = Specification
                                .allOf(companySpecification.searchByName(pageRequestValidate.getKeyword()));
                return companyRepository.findAll(spec, pageable)
                                .map(companyMapper::companyToCompanyDTO);
        }

        @Override
        public CompanyDTO getCompanyById(String idCompany) {
                Company company = companyRepository.findById(idCompany)
                                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id company!"));
                return companyMapper.companyToCompanyDTO(company);
        }

        @Override
        @Transactional
        public CompanyDTO saveCompany(SaveUpdateCompanyRequest saveUpdateCompanyRequest) {
                CompanySize companySize = companySizeRepository.findById(saveUpdateCompanyRequest.getCompanySizeId())
                                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));
                Company company = companyMapper.saveCompanyMapper(companySize, saveUpdateCompanyRequest);
                return companyMapper.companyToCompanyDTO(companyRepository.save(company));
        }

        @Override
        @Transactional
        public CompanyDTO updateCompany(String idCompany, SaveUpdateCompanyRequest saveUpdateCompanyRequest) {
                CompanySize companySize = companySizeRepository.findById(saveUpdateCompanyRequest.getCompanySizeId())
                                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));

                Company company = companyRepository.findById(idCompany)
                                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id company!"));

                Company mappedCompany = companyMapper.updateCompanyMapper(idCompany, companySize,
                                saveUpdateCompanyRequest);
                mappedCompany.setCreatedDate(company.getCreatedDate());
                return companyMapper.companyToCompanyDTO(companyRepository.save(mappedCompany));
        }

        @Override
        @Transactional
        public CompanyDTO deleteCompany(String idCompany) {
                Company company = companyRepository.findById(idCompany)
                                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id company!"));
                companyRepository.delete(company);
                return companyMapper.companyToCompanyDTO(company);
        }
}
