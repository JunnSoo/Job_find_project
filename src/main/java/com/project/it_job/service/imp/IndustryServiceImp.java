package com.project.it_job.service.imp;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.IndustryMapper;
import com.project.it_job.repository.IndustryRepository;
import com.project.it_job.request.IndustryRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.IndustryService;
import com.project.it_job.specification.IndustrySpecification;
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
public class IndustryServiceImp implements IndustryService {

    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;
    private final PageCustomHelper pageCustomHelper;
    private final IndustrySpecification industrySpecification;

    @Override
    public List<IndustryDTO> getAll() {
        return industryRepository.findAll()
                .stream()
                .map(industryMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<IndustryDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        //Search
        Specification<Industry> spec = industrySpecification.searchByName(pageRequestValidate.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return industryRepository.findAll(spec, pageable)
                .map(industryMapper::toDTO);
    }

    @Override
    public IndustryDTO getById(int id) {
        Industry industry = industryRepository.findById(id).
                orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry với ID: "+ id));
        return industryMapper.toDTO(industry);
    }

    @Override
    @Transactional
    public IndustryDTO create(IndustryRequest request) {
        Industry entity = industryMapper.saveIndustry(request);
        return industryMapper.toDTO(industryRepository.save(entity));
    }

    @Override
    @Transactional
    public IndustryDTO update(int id, IndustryRequest request) {
        industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry id: "+ id));
        Industry entity = industryMapper.updateIndustry(id, request);
        return industryMapper.toDTO(industryRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(int id) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry id: "+ id));
        industryRepository.delete(industry);
    }
}
