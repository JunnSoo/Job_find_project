package com.project.it_job.service.imp;

import com.project.it_job.dto.JobDTO;
import com.project.it_job.entity.Job;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.JobMapper;
import com.project.it_job.repository.JobRepository;
import com.project.it_job.request.JobRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.JobService;
import com.project.it_job.specification.JobSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final PageCustomHelpper pageCustomHelpper;
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
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize());

        Specification<Job> spec = Specification.allOf(jobSpecification.searchByName(pageRequestValidate.getKeyword()));
        return jobRepository.findAll(spec, pageable).map(job -> jobMapper.toDTO(job));
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
        Job job = jobMapper.toEntity(request);
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    @Transactional
    public JobDTO updateJob(int id, JobRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Job ID: " + id));
        jobMapper.updateEntity(job, request);
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
