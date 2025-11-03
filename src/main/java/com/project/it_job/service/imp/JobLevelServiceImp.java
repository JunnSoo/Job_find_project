package com.project.it_job.service.imp;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.JobLevelMapper;
import com.project.it_job.repository.JobLevelRepository;
import com.project.it_job.request.JobLevelRequest;
import com.project.it_job.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobLevelServiceImp implements JobLevelService {

    @Autowired
    private JobLevelRepository jobLevelRepository;

    @Autowired
    private JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevelDTO> getAll() {
        return jobLevelRepository.findAll()
                .stream()
                .map(jobLevelMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobLevelDTO getById(int id) {
        JobLevel jobLevel = jobLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Joblevel với ID: " + id));
        return jobLevelMapper.toDTO(jobLevel);
    }

    @Override
    public JobLevelDTO create(JobLevelRequest request){
        JobLevel jobLevel = new JobLevel();
        jobLevel.setName(request.getName());
        return jobLevelMapper.toDTO(jobLevelRepository.save(jobLevel));
    }

    @Override
    public JobLevelDTO update(int id, JobLevelRequest request){
        JobLevel jobLevel = jobLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Joblevel ID: " + id));
        jobLevel.setName(request.getName());
        return jobLevelMapper.toDTO(jobLevelRepository.save(jobLevel));
    }

    @Override
    public void delete(int id) {
        JobLevel jobLevel = jobLevelRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Joblevel ID: " + id));
        jobLevelRepository.delete(jobLevel);
    }
}
