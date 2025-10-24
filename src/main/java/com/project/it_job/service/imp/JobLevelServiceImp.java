package com.project.it_job.service.imp;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.entity.JobLevel;
import com.project.it_job.mapper.JobLevelMapper;
import com.project.it_job.repository.JobLevelRepository;
import com.project.it_job.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .toList();
    }

    @Override
    public Optional<JobLevelDTO> getById(int id) {
        return jobLevelRepository.findById(id)
                .map(jobLevelMapper::toDTO);
    }

    @Override
    public JobLevelDTO create(JobLevelDTO dto) {
        JobLevel jobLevel = jobLevelMapper.toEntity(dto);
        return jobLevelMapper.toDTO(jobLevelRepository.save(jobLevel));
    }

    @Override
    public JobLevelDTO update(int id, JobLevelDTO dto) {
        Optional<JobLevel> optional = jobLevelRepository.findById(id);
        if (optional.isPresent()) {
            JobLevel jobLevel = optional.get();
            jobLevel.setName(dto.getName());
            return jobLevelMapper.toDTO(jobLevelRepository.save(jobLevel));
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Optional<JobLevel> optional = jobLevelRepository.findById(id);
        if (optional.isPresent()) {
            jobLevelRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
