package com.project.it_job.service;

import com.project.it_job.dto.GroupCoreSkillDto;
import com.project.it_job.request.GroupCoreSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupCoreSkillService {
    List<GroupCoreSkillDto> getAllGroupCoreSkill();
    Page<GroupCoreSkillDto> getAllGroupCoreSkillPage(PageRequestCustom pageRequestCustom);
    GroupCoreSkillDto getGroupCoreSkillById(Integer id);
    GroupCoreSkillDto createGroupCoreSkill(GroupCoreSkillRequest request);
    GroupCoreSkillDto updateGroupCoreSkill(int id, GroupCoreSkillRequest request);
    GroupCoreSkillDto deleteGroupCoreSkill(int id);
}
