package com.project.codinviec.service.imp;

import com.project.codinviec.dto.DegreeLevelDTO;
import com.project.codinviec.entity.DegreeLevel;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.DegreeLevelMapper;
import com.project.codinviec.repository.DegreeLevelRepository;
import com.project.codinviec.request.DegreeLevelRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.DegreeLevelService;
import com.project.codinviec.specification.DegreeLevelSpecification;
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
public class DegreeLevelServiceImp implements DegreeLevelService {

    private final DegreeLevelRepository degreeLevelRepository;
    private final DegreeLevelMapper degreeLevelMapper;
    private final PageCustomHelper pageCustomHelper;
    private final DegreeLevelSpecification degreeLevelSpecification;

    @Override
    public List<DegreeLevelDTO> getAll() {
        return degreeLevelRepository.findAll()
                .stream()
                .map(degreeLevelMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<DegreeLevelDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        //Search
        Specification<DegreeLevel> spec = degreeLevelSpecification.searchByName(pageRequestValidate.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return degreeLevelRepository.findAll(spec, pageable)
                .map(degreeLevelMapper::toDTO);
    }

    @Override
    public DegreeLevelDTO getById(int id) {
        DegreeLevel degreeLevel = degreeLevelRepository.findById(id)
                        .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy DegreeLevel ID: " + id));
        return degreeLevelMapper.toDTO(degreeLevel);
    }

    @Override
    @Transactional
    public DegreeLevelDTO create(DegreeLevelRequest request) {
        DegreeLevel entity = degreeLevelMapper.saveDegreeLevel(request);
        return degreeLevelMapper.toDTO(degreeLevelRepository.save(entity));
    }

    @Override
    @Transactional
    public DegreeLevelDTO update(int id, DegreeLevelRequest dto) {
        degreeLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy DegreeLevel ID: " + id));
        DegreeLevel entity = degreeLevelMapper.updateDegreeLevel(id, dto);
        return degreeLevelMapper.toDTO(degreeLevelRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(int id) {
        DegreeLevel degreeLevel = degreeLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy DegreeLevel ID: " + id));
        degreeLevelRepository.delete(degreeLevel);
    }
}
