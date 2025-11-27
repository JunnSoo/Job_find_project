package com.project.codinviec.service.imp;

import com.project.codinviec.dto.AvailableSkillExperienceDTO;
import com.project.codinviec.entity.*;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.AvailableSkillExperienceMapper;
import com.project.codinviec.repository.*;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.AvailableSkillExperienceRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.service.AvailableSkillExperienceService;
import com.project.codinviec.util.helper.PageCustomHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableSkillExperienceServiceImp implements AvailableSkillExperienceService {

    private final AvailableSkillExperienceRepository availableSkillExperienceRepository;
    private final GroupCoreSkillRepository groupCoreSkillRepository;
    private final AvailableSkillRepository availableSkillRepository;
    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final AvailableSkillExperienceMapper mapper;
    private final PageCustomHelper pageCustomHelper;

    @Override
    public List<AvailableSkillExperienceDTO> getAllAvailableSkillExperience() {
        return availableSkillExperienceRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Page<AvailableSkillExperienceDTO> getAllAvailableSkillExperiencePage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return availableSkillExperienceRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public List<AvailableSkillExperienceDTO> getAvailableSkillExperienceByUser(String userId) {
        return availableSkillExperienceRepository.findByUser_Id(userId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public AvailableSkillExperienceDTO getAvailableSkillExperienceById(Integer id) {
        AvailableSkillExperience ase = availableSkillExperienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id available_skill_experience"));
        return mapper.toDto(ase);
    }

    @Override
    @Transactional
    public AvailableSkillExperienceDTO createAvailableSkillExperience(AvailableSkillExperienceRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id user"));

        GroupCoreSkill groupCoreSkill = null;
        if (request.getGroupCoreId() != null) {
            groupCoreSkill = groupCoreSkillRepository.findById(request.getGroupCoreId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id group_core_skill"));
        }

        AvailableSkill availableSkill = availableSkillRepository.findById(request.getAvailableSkillId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id available_skill"));

        Experience experience = experienceRepository.findById(request.getExperienceId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id experience"));

        // chặn trùng
        if (availableSkillExperienceRepository.existsByUser_IdAndAvailableSkill_IdAndExperience_Id(
                user.getId(), availableSkill.getId(), experience.getId())) {
            throw new NotFoundIdExceptionHandler("User đã tồn tại available_skill + experience này");
        }

        AvailableSkillExperience ase = mapper.saveAvailableSkillExperience(user, groupCoreSkill, availableSkill, experience, request);

        availableSkillExperienceRepository.save(ase);
        return mapper.toDto(ase);
    }

    @Override
    @Transactional
    public AvailableSkillExperienceDTO updateAvailableSkillExperience(int id, AvailableSkillExperienceRequest request) {
        AvailableSkillExperience ase = availableSkillExperienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id available_skill_experience"));

        if (request.getGroupCoreId() != null) {
            GroupCoreSkill groupCoreSkill = groupCoreSkillRepository.findById(request.getGroupCoreId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id group_core_skill"));
            ase.setGroupCoreSkill(groupCoreSkill);
        }

        if (request.getAvailableSkillId() != null) {
            AvailableSkill availableSkill = availableSkillRepository.findById(request.getAvailableSkillId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id available_skill"));
            ase.setAvailableSkill(availableSkill);
        }

        if (request.getExperienceId() != null) {
            Experience experience = experienceRepository.findById(request.getExperienceId())
                    .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id experience"));
            ase.setExperience(experience);
        }

        AvailableSkillExperience updated = availableSkillExperienceRepository.save(ase);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public AvailableSkillExperienceDTO deleteAvailableSkillExperience(int id) {
        AvailableSkillExperience ase = availableSkillExperienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id available_skill_experience"));
        availableSkillExperienceRepository.delete(ase);
        return mapper.toDto(ase);
    }
}
