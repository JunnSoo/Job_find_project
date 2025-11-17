package com.project.it_job.service;

import com.project.it_job.dto.GroupCoreSkillDTO;
import com.project.it_job.request.GroupCoreSkillRequest;
import com.project.it_job.request.PageRequestCustom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GroupCoreSkillService {
    List<GroupCoreSkillDTO> getAllGroupCoreSkill();
    Page<GroupCoreSkillDTO> getAllGroupCoreSkillPage(PageRequestCustom pageRequestCustom);
    GroupCoreSkillDTO getGroupCoreSkillById(Integer id);
    GroupCoreSkillDTO createGroupCoreSkill(GroupCoreSkillRequest request);
    GroupCoreSkillDTO updateGroupCoreSkill(int id, GroupCoreSkillRequest request);
    GroupCoreSkillDTO deleteGroupCoreSkill(int id);
}
