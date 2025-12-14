package com.project.codinviec.service.imp;

import com.project.codinviec.dto.CompanyAddressDTO;
import com.project.codinviec.entity.CompanyAddress;
import com.project.codinviec.entity.Province;
import com.project.codinviec.entity.Ward;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.CompanyAddressMapper;
import com.project.codinviec.repository.CompanyAddressRepository;
import com.project.codinviec.repository.ProvinceRepository;
import com.project.codinviec.repository.WardRepository;
import com.project.codinviec.request.CompanyAddressRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.CompanyAddressService;
import com.project.codinviec.specification.CompanyAddressSpeciification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyAddressImp implements CompanyAddressService {
    private final CompanyAddressRepository companyAddressRepository;
    private final CompanyAddressMapper companyAddressMapper;
    private final PageCustomHelper pageCustomHelper;
    private final CompanyAddressSpeciification companyAddressSpeciification;
    private final ProvinceRepository provinceRepository;
    private final WardRepository wardRepository;


    @Override
    public List<CompanyAddressDTO> getAllCompanyAddresses() {
        return companyAddressRepository.findAll().stream().map(companyAddressMapper::toCompanyAddressDTO).toList();
    }

    @Override
    public Page<CompanyAddressDTO> getAllCompanyAddressesPage(PageRequestCustom pageRequestCustom) {
        // Validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "idAsc" -> Sort.by(Sort.Direction.ASC, "id");
            case "idDesc" -> Sort.by(Sort.Direction.DESC, "id");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };
        // Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(),sort);
        Specification<CompanyAddress> spec = Specification
                .allOf(companyAddressSpeciification.searchById(pageRequestValidate.getKeyword()));

        return companyAddressRepository.findAll(spec,pageable).map(companyAddressMapper::toCompanyAddressDTO);
    }

    @Override
    public CompanyAddressDTO getCompanyAddressById(Integer id) {
        CompanyAddress companyAddress = companyAddressRepository.findById(id).orElseThrow(
                ()->  new NotFoundIdExceptionHandler("Không tìm thấy company address")
        );
        return companyAddressMapper.toCompanyAddressDTO(companyAddress);
    }

    @Override
    public CompanyAddressDTO saveCompanyAddress(CompanyAddressRequest companyAddressRequest) {
        Province province = provinceRepository.findById(companyAddressRequest.getProvinceId()).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy Province")
        );

        Ward ward = wardRepository.findById(companyAddressRequest.getWardId()).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy Ward")
        );
        return companyAddressMapper.toCompanyAddressDTO(
                companyAddressRepository.save(
                        companyAddressMapper.saveCompanyMapper(
                                companyAddressRequest,province,ward)));
    }

    @Override
    public CompanyAddressDTO updateCompanyAddress(Integer id, CompanyAddressRequest companyAddressRequest) {
        Province province = provinceRepository.findById(companyAddressRequest.getProvinceId()).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy Province")
        );

        Ward ward = wardRepository.findById(companyAddressRequest.getWardId()).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy Ward")
        );
        return companyAddressMapper.toCompanyAddressDTO(
                companyAddressRepository.save(
                        companyAddressMapper.updateCompanyMapper(
                                id, companyAddressRequest,province,ward)));
    }

    @Override
    public CompanyAddressDTO deleteCompanyAddress(Integer id) {
        CompanyAddress companyAddress = companyAddressRepository.findById(id).orElseThrow(
                ()->  new NotFoundIdExceptionHandler("Không tìm thấy company address")
        );
        companyAddressRepository.delete(companyAddress);
        return companyAddressMapper.toCompanyAddressDTO(companyAddress);
    }
}
