package com.project.codinviec.service;

import com.project.codinviec.dto.GroupCoreSkillDTO;
import com.project.codinviec.request.GroupCoreSkillRequest;
import com.project.codinviec.request.PageRequestCustom;
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
