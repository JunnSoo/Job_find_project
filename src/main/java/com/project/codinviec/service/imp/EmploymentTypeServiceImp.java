package com.project.codinviec.service.imp;

import com.project.codinviec.dto.EmploymentTypeDTO;
import com.project.codinviec.entity.EmploymentType;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.EmploymentTypeMapper;
import com.project.codinviec.repository.EmploymentTypeRepository;
import com.project.codinviec.request.EmploymentTypeRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.EmploymentTypeService;
import com.project.codinviec.specification.EmploymentTypeSpecification;
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
public class EmploymentTypeServiceImp implements EmploymentTypeService {

    private final EmploymentTypeRepository employmentTypeRepository;
    private final EmploymentTypeMapper employmentTypeMapper;
    private final PageCustomHelper pageCustomHelper;
    private final EmploymentTypeSpecification employmentTypeSpecification;

    @Override
    public List<EmploymentTypeDTO> getAll() {
        return employmentTypeRepository.findAll()
                .stream()
                .map(employmentTypeMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<EmploymentTypeDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        //Search
        Specification<EmploymentType> spec = employmentTypeSpecification.searchByName(pageRequestValidate.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return employmentTypeRepository.findAll(spec, pageable)
                .map(employmentTypeMapper::toDTO);
    }

    @Override
    public EmploymentTypeDTO getById(int id) {
        EmploymentType employmentType = employmentTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy EmploymentType với ID: " + id));
        return employmentTypeMapper.toDTO(employmentType);
    }

    @Override
    @Transactional
    public EmploymentTypeDTO create(EmploymentTypeRequest request) {
        EmploymentType entity = employmentTypeMapper.saveEmploymentType(request);
        return employmentTypeMapper.toDTO(employmentTypeRepository.save(entity));
    }

    @Override
    @Transactional
    public EmploymentTypeDTO update(int id, EmploymentTypeRequest request) {
        employmentTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy EmploymentType ID: " + id));
        EmploymentType entity = employmentTypeMapper.updateEmploymentType(id, request);
        return employmentTypeMapper.toDTO(employmentTypeRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(int id) {
       EmploymentType employmentType = employmentTypeRepository.findById(id)
               .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy EmploymentType ID: " + id));
       employmentTypeRepository.delete(employmentType);
    }
}
