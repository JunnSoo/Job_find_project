package com.project.it_job.service.imp;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.entity.Province;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.ProvinceMapper;
import com.project.it_job.repository.ProvinceRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.ProvinceRequest;
import com.project.it_job.service.ProvinceService;
import com.project.it_job.specification.ProvinceSpecification;
import com.project.it_job.util.helper.PageCustomHelper;
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
