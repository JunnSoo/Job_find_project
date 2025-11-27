package com.project.codinviec.service.imp;

import com.project.codinviec.dto.CompanySizeDTO;
import com.project.codinviec.entity.CompanySize;
import com.project.codinviec.exception.common.ConflictExceptionHandler;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.CompanySizeMapper;
import com.project.codinviec.repository.CompanySizeRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateCompanySizeRequest;
import com.project.codinviec.service.CompanySizeService;
import com.project.codinviec.specification.CompanySizeSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanySizeServiceImp implements CompanySizeService {
    private final CompanySizeRepository companySizeRepository;
    private final CompanySizeMapper companySizeMapper;
    private final PageCustomHelper pageCustomHelper;
    private final CompanySizeSpecification companySizeSpecification;

    @Override
    public List<CompanySizeDTO> getAllCompany() {
        return companySizeRepository.findAll().stream().map(companySizeMapper::companySizeToCompanySizeDTO).toList();
    }

    @Override
    public Page<CompanySizeDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<CompanySize> spec = Specification.allOf(
                companySizeSpecification.searchByName(pageRequestCustom.getKeyword()));

        return companySizeRepository.findAll(spec, pageable).map(
                companySizeMapper::companySizeToCompanySizeDTO);
    }

    @Override
    public CompanySizeDTO getCompanyById(Integer id) {
        CompanySize companySize = companySizeRepository.findById(id)
                .orElseThrow( ()-> new NotFoundIdExceptionHandler("Không tìm thấy id company size"));
        return  companySizeMapper.companySizeToCompanySizeDTO(companySize);
    }

    @Override
    @Transactional
    public CompanySizeDTO saveCompanySize(SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest) {
        try {
            CompanySize companySize = companySizeMapper.saveCompanySizeMapper(saveUpdateCompanySizeRequest);
            return companySizeMapper.companySizeToCompanySizeDTO(companySizeRepository.save(companySize));
        } catch (Exception e){
            throw new ConflictExceptionHandler("Lỗi thêm category size!");
        }

    }

    @Override
    @Transactional
    public CompanySizeDTO updateCompanySize(Integer idCompanySize, SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest) {
        try {
            companySizeRepository.findById(idCompanySize)
                    .orElseThrow(()-> new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));
            CompanySize mappedCompanySize = companySizeMapper.updateCompanySizeMapper(idCompanySize,saveUpdateCompanySizeRequest);
            return companySizeMapper.companySizeToCompanySizeDTO(companySizeRepository.save(mappedCompanySize));
        } catch (Exception e){
            throw new ConflictExceptionHandler("Lỗi cập nhật category size!");
        }
    }

    @Override
    @Transactional
    public CompanySizeDTO deleteCompanySize(Integer id) {
        CompanySize companySize = companySizeRepository.findById(id)
                .orElseThrow( ()-> new NotFoundIdExceptionHandler("Không tìm thấy id company size"));
        companySizeRepository.delete(companySize);
        return companySizeMapper.companySizeToCompanySizeDTO(companySize);
    }
}
