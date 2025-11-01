package com.project.it_job.service.imp;

import com.project.it_job.dto.GroupCoreSkillDto;
import com.project.it_job.entity.GroupCoreSkill;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.GroupCoreSkillMapper;
import com.project.it_job.repository.GroupCoreSkillRepository;
import com.project.it_job.request.GroupCoreSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.GroupCoreSkillService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCoreSkillServiceImp implements GroupCoreSkillService {

    private final GroupCoreSkillRepository repository;
    private final GroupCoreSkillMapper mapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<GroupCoreSkillDto> getAllGroupCoreSkill() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Page<GroupCoreSkillDto> getAllGroupCoreSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public GroupCoreSkillDto getGroupCoreSkillById(Integer id) {
        GroupCoreSkill entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id group_core_skill"));
        return mapper.toDto(entity);
    }

    @Override
    public GroupCoreSkillDto createGroupCoreSkill(GroupCoreSkillRequest request) {
        GroupCoreSkill entity = GroupCoreSkill.builder()
                .name(request.getName())
                .build();
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public GroupCoreSkillDto updateGroupCoreSkill(int id, GroupCoreSkillRequest request) {
        GroupCoreSkill entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id group_core_skill"));

        entity.setName(request.getName() != null && !request.getName().isEmpty()
                ? request.getName()
                : entity.getName());

        GroupCoreSkill updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    @Override
    public GroupCoreSkillDto deleteGroupCoreSkill(int id) {
        GroupCoreSkill entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id group_core_skill"));
        repository.delete(entity);
        return mapper.toDto(entity);
    }
}
