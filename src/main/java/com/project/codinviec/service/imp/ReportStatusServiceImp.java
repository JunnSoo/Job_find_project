package com.project.codinviec.service.imp;

import com.project.codinviec.dto.ReportStatusDTO;
import com.project.codinviec.entity.ReportStatus;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.ReportStatusMapper;
import com.project.codinviec.repository.ReportStatusRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.ReportStatusRequest;
import com.project.codinviec.service.ReportStatusService;
import com.project.codinviec.specification.ReportStatusSpecification;
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
public class ReportStatusServiceImp implements ReportStatusService {
    private final ReportStatusRepository reportStatusRepository;
    private final ReportStatusMapper reportStatusMapper;
    private final PageCustomHelper pageCustomHelper;
    private final ReportStatusSpecification reportStatusSpecification;

    @Override
    public List<ReportStatusDTO> getAllStatus() {
        return reportStatusRepository.findAll()
                .stream()
                .map(reportStatusMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public Page<ReportStatusDTO> getAllStatusWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(req);

        //Search
        Specification<ReportStatus> spec = reportStatusSpecification.searchByName(pageRequestValidate.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "nameAsc" -> Sort.by(Sort.Direction.ASC, "name");
            case "nameDesc" -> Sort.by(Sort.Direction.DESC, "name");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return reportStatusRepository.findAll(spec, pageable)
                .map(reportStatusMapper::toDTO);
    }

    @Override
    public ReportStatusDTO getStatusById(int id) {
        ReportStatus status = reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        return reportStatusMapper.toDTO(status);
    }

    @Override
    @Transactional
    public ReportStatusDTO createStatus(ReportStatusRequest request) {
        ReportStatus entity = reportStatusMapper.saveReportStatus(request);
        return reportStatusMapper.toDTO(reportStatusRepository.save(entity));
    }

    @Override
    @Transactional
    public ReportStatusDTO updateStatus(int id, ReportStatusRequest request) {
        reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        ReportStatus entity = reportStatusMapper.updateReportStatus(id, request);
        return reportStatusMapper.toDTO(reportStatusRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(int id) {
        ReportStatus status = reportStatusRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy ReportStatus với ID: " + id));
        reportStatusRepository.delete(status);
    }
}
