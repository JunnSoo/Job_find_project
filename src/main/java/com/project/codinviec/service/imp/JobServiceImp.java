package com.project.codinviec.service.imp;

import com.project.codinviec.dto.JobDTO;
import com.project.codinviec.entity.Job;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.JobMapper;
import com.project.codinviec.repository.JobRepository;
import com.project.codinviec.request.JobRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.JobService;
import com.project.codinviec.specification.JobSpecification;
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
public class JobServiceImp implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final PageCustomHelper pageCustomHelper;
    private final JobSpecification jobSpecification;

    @Override
    public List<JobDTO> getAllJob() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toDTO)
                .toList();
    }

    @Override
    public Page<JobDTO> getALLjobPage(PageRequestCustom pageRequestCustom){
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());

        Specification<Job> spec = Specification.allOf(jobSpecification.searchByName(pageRequestValidate.getKeyword()));
        return jobRepository.findAll(spec, pageable)
                .map(jobMapper::toDTO);
    }

    @Override
    public JobDTO getJobById(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        return jobMapper.toDTO(job);
    }

    @Override
    @Transactional
    public JobDTO createJob(JobRequest request) {
        Job job = jobMapper.saveJob(request);
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    @Transactional
    public JobDTO updateJob(int id, JobRequest request) {
        jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        Job job = jobMapper.updateJob(id, request);
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    @Transactional
    public void deleteJob(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        jobRepository.delete(job);
    }


}
