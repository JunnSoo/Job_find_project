package com.project.it_job.service.imp;

import com.project.it_job.dto.GroupCoreSkillDTO;
import com.project.it_job.entity.GroupCoreSkill;
import com.project.it_job.exception.common.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.GroupCoreSkillMapper;
import com.project.it_job.repository.GroupCoreSkillRepository;
import com.project.it_job.request.GroupCoreSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.GroupCoreSkillService;
import com.project.it_job.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCoreSkillServiceImp implements GroupCoreSkillService {

    private final GroupCoreSkillRepository repository;
    private final GroupCoreSkillMapper mapper;
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<GroupCoreSkillDTO> getAllGroupCoreSkill() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Page<GroupCoreSkillDTO> getAllGroupCoreSkillPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public GroupCoreSkillDTO getGroupCoreSkillById(Integer id) {
        GroupCoreSkill entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id group_core_skill"));
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public GroupCoreSkillDTO createGroupCoreSkill(GroupCoreSkillRequest request) {
        GroupCoreSkill entity = mapper.saveGroupCoreSkill(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public GroupCoreSkillDTO updateGroupCoreSkill(int id, GroupCoreSkillRequest request) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id group_core_skill"));

        GroupCoreSkill entity = mapper.updateGroupCoreSkill(id, request);
        GroupCoreSkill updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public GroupCoreSkillDTO deleteGroupCoreSkill(int id) {
        GroupCoreSkill entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id group_core_skill"));
        repository.delete(entity);
        return mapper.toDto(entity);
    }
}
