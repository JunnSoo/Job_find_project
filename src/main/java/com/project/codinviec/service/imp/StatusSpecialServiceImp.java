package com.project.codinviec.service.imp;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.dto.StatusSpecialDTO;
import com.project.codinviec.entity.StatusSpecial;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.JobMapper;
import com.project.codinviec.mapper.StatusSpecialMapper;
import com.project.codinviec.repository.JobRepository;
import com.project.codinviec.repository.StatusSpecialRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.StatusSpecialService;
import com.project.codinviec.specification.StatusSpecialSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
import jakarta.transaction.Transactional;
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
public class StatusSpecialServiceImp implements StatusSpecialService {

    private final StatusSpecialRepository statusSpecialRepository;
    private final StatusSpecialMapper statusSpecialMapper;
    private final PageCustomHelper pageCustomHelper;
    private final StatusSpecialSpecification statusSpecialSpecification;
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public List<StatusSpecialDTO> getAll() {
        return statusSpecialRepository.findAll()
                .stream()
                .map(statusSpecialMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<StatusSpecialDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        Specification<StatusSpecial> spec = statusSpecialSpecification.searchByTitle(pageRequestValidate.getKeyword());

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "titleAsc" -> Sort.by(Sort.Direction.ASC, "title");
            case "titleDesc" -> Sort.by(Sort.Direction.DESC, "title");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return statusSpecialRepository.findAll(spec, pageable)
                .map(statusSpecialMapper::toDTO);
    }

    @Override
    public StatusSpecialDTO getById(int id) {
        StatusSpecial s = statusSpecialRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy StatusSpecial ID: " + id));
        return statusSpecialMapper.toDTO(s);
    }

    @Override
    @Transactional
    public StatusSpecialDTO create(com.project.codinviec.request.StatusSpecialRequest request) {
        StatusSpecial entity = statusSpecialMapper.saveStatusSpecial(request);
        return statusSpecialMapper.toDTO(statusSpecialRepository.save(entity));
    }

    @Override
    @Transactional
    public StatusSpecialDTO update(int id, com.project.codinviec.request.StatusSpecialRequest request) {
        statusSpecialRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy StatusSpecial ID: " + id));
        StatusSpecial entity = statusSpecialMapper.updateStatusSpecial(id, request);
        return statusSpecialMapper.toDTO(statusSpecialRepository.save(entity));
    }

    @Override
    @Transactional
    public StatusSpecialDTO delete(int id) {
        StatusSpecial s = statusSpecialRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy StatusSpecial ID: " + id));
        statusSpecialRepository.delete(s);
        return statusSpecialMapper.toDTO(s);
    }
}

