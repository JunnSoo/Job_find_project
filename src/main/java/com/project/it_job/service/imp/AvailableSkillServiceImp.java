package com.project.it_job.service.imp;

import com.project.it_job.dto.AvailableSkillDto;
import com.project.it_job.entity.AvailableSkill;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.AvailableSkillMapper;
import com.project.it_job.repository.AvailableSkillRepository;
import com.project.it_job.request.AvailableSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.AvailableSkillService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableSkillServiceImp implements AvailableSkillService {

    private final AvailableSkillRepository availableSkillRepository;
    private final AvailableSkillMapper availableSkillMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<AvailableSkillDto> getAllAvailableSkill() {
        return availableSkillRepository.findAll()
                .stream()
                .map(availableSkillMapper::toDto)
                .toList();
    }

    @Override
    public Page<AvailableSkillDto> getAllAvailableSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return availableSkillRepository.findAll(pageable).map(availableSkillMapper::toDto);
    }

    @Override
    public AvailableSkillDto getAvailableSkillById(Integer id) {
        AvailableSkill entity = availableSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id available_skill"));
        return availableSkillMapper.toDto(entity);
    }

    @Override
    public AvailableSkillDto createAvailableSkill(AvailableSkillRequest request) {
        AvailableSkill entity = AvailableSkill.builder()
                .name(request.getName())
                .build();
        availableSkillRepository.save(entity);
        return availableSkillMapper.toDto(entity);
    }

    @Override
    public AvailableSkillDto updateAvailableSkill(int id, AvailableSkillRequest request) {
        AvailableSkill entity = availableSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id available_skill"));

        entity.setName(request.getName() != null && !request.getName().isEmpty()
                ? request.getName()
                : entity.getName());

        AvailableSkill updated = availableSkillRepository.save(entity);
        return availableSkillMapper.toDto(updated);
    }

    @Override
    public AvailableSkillDto deleteAvailableSkill(int id) {
        AvailableSkill entity = availableSkillRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id available_skill"));
        availableSkillRepository.delete(entity);
        return availableSkillMapper.toDto(entity);
    }
}
