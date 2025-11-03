package com.project.it_job.service.imp;

import com.project.it_job.dto.IndustryDTO;
import com.project.it_job.entity.Industry;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.IndustryMapper;
import com.project.it_job.repository.IndustryRepository;
import com.project.it_job.request.IndustryRequest;
import com.project.it_job.service.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryServiceImp implements IndustryService {

    private final IndustryRepository industryRepository;

    private final IndustryMapper industryMapper;
    @Override
    public List<IndustryDTO> getAll() {
        return industryRepository.findAll()
                .stream()
                .map(industryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IndustryDTO getById(int id) {
        Industry industry = industryRepository.findById(id).
                orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry với ID: "+ id));
        return industryMapper.toDTO(industry);
    }

    @Override
    public IndustryDTO create(IndustryRequest request) {
        Industry industry = new Industry();
        industry.setName(request.getName());
        return industryMapper.toDTO(industryRepository.save(industry));
    }

    @Override
    public IndustryDTO update(int id, IndustryRequest request) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry id: "+ id));
        industry.setName(request.getName());
        return industryMapper.toDTO(industryRepository.save(industry));
    }

    @Override
    public void delete(int id) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy Industry id: "+ id));
        industryRepository.delete(industry);
    }
}
