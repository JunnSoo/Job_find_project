package com.project.it_job.service.imp;

import com.project.it_job.entity.Industry;
import com.project.it_job.repository.IndustryRepository;
import com.project.it_job.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustryServiceImp implements IndustryService {
    @Autowired
    private IndustryRepository industryRepository;
    @Override
    public List<Industry> getAll() {
        return industryRepository.findAll();
    }

    @Override
    public Optional<Industry> getById(int id) {
        return industryRepository.findById(id);
    }

    @Override
    public Industry create(Industry industry) {
        return industryRepository.save(industry);
    }

    @Override
    public Industry update(int id, Industry industry) {
        Optional<Industry> existing = industryRepository.findById(id);
        if (existing.isPresent()) {
            Industry updateEntity = existing.get();
            updateEntity.setName(industry.getName());
            return industryRepository.save(updateEntity);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Optional<Industry> existing = industryRepository.findById(id);
        if (existing.isPresent()) {
            industryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
