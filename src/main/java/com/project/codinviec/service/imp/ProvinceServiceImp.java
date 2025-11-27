package com.project.codinviec.service.imp;

import com.project.codinviec.dto.ProvinceDTO;
import com.project.codinviec.entity.Province;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.ProvinceMapper;
import com.project.codinviec.repository.ProvinceRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ProvinceRequest;
import com.project.codinviec.service.ProvinceService;
import com.project.codinviec.specification.ProvinceSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImp implements ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;
    private final PageCustomHelper pageCustomHelper;
    private final ProvinceSpecification provinceSpecification;

    @Override
    public List<ProvinceDTO> getAll() {
        return provinceRepository.findAll()
                .stream()
                .map(provinceMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<ProvinceDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        //Search
        Specification<Province> spec = provinceSpecification.searchByName(pageRequestValidate.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return provinceRepository.findAll(spec, pageable)
                .map(provinceMapper::toDTO);
    }

    @Override
    public ProvinceDTO getById(int id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Province ID: " + id));
        return provinceMapper.toDTO(province);
    }

    @Override
    @Transactional
    public ProvinceDTO create(ProvinceRequest request) {
        Province entity = provinceMapper.saveProvince(request);
        return provinceMapper.toDTO(provinceRepository.save(entity));
    }

    @Override
    @Transactional
    public ProvinceDTO update(int id, ProvinceRequest request) {
        provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Province ID: " + id));
        Province entity = provinceMapper.updateProvince(id, request);
        return provinceMapper.toDTO(provinceRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(int id) {
        Province province = provinceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Province ID: " + id));
        provinceRepository.delete(province);
    }
}
