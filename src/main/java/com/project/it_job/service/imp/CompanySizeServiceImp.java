package com.project.it_job.service.imp;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.entity.CompanySize;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.CompanySizeMapper;
import com.project.it_job.repository.CompanySizeRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCompanySizeRequest;
import com.project.it_job.service.CompanySizeService;
import com.project.it_job.specification.CompanySizeSpecification;
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
public class CompanySizeServiceImp implements CompanySizeService {
    private final CompanySizeRepository companySizeRepository;
    private final CompanySizeMapper companySizeMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final CompanySizeSpecification companySizeSpecification;

    @Override
    public List<CompanySizeDTO> getAllCompany() {
        return companySizeRepository.findAll().stream().map(cs -> companySizeMapper.companySizeToCompanySizeDTO(cs)).toList();
    }

    @Override
    public Page<CompanySizeDTO> getAllCompanyPage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<CompanySize> spec = Specification.allOf(
                companySizeSpecification.searchByName(pageRequestCustom.getKeyword()));

        return companySizeRepository.findAll(spec, pageable).map(
                cs -> companySizeMapper.companySizeToCompanySizeDTO(cs));
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
            throw new ConflictException("Lỗi thêm category size!");
        }

    }

    @Override
    @Transactional
    public CompanySizeDTO updateCompanySize(Integer idCompanySize, SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest) {
        try {
            CompanySize companySize = companySizeRepository.findById(idCompanySize)
                    .orElseThrow(()-> new NotFoundIdExceptionHandler("Không tìm thấy id company size!"));
            CompanySize mappedCompanySize = companySizeMapper.updateCompanySizeMapper(idCompanySize,saveUpdateCompanySizeRequest);
            return companySizeMapper.companySizeToCompanySizeDTO(companySizeRepository.save(mappedCompanySize));
        } catch (Exception e){
            throw new ConflictException("Lỗi cập nhật category size!");
        }
    }

    @Override
    public CompanySizeDTO deleteCompanySize(Integer id) {
        CompanySize companySize = companySizeRepository.findById(id)
                .orElseThrow( ()-> new NotFoundIdExceptionHandler("Không tìm thấy id company size"));
        companySizeRepository.delete(companySize);
        return companySizeMapper.companySizeToCompanySizeDTO(companySize);
    }
}
