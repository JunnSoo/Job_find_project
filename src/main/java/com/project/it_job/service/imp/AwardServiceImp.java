package com.project.it_job.service.imp;

import com.project.it_job.dto.AwardDto;
import com.project.it_job.entity.Award;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.AwardMapper;
import com.project.it_job.repository.AwardRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.AwardRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.AwardService;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardServiceImp implements AwardService {

    private final AwardRepository awardRepository;
    private final UserRepository userRepository;
    private final AwardMapper awardMapper;
    private final PageCustomHelpper pageCustomHelpper;

    @Override
    public List<AwardDto> getAllAward() {
        return awardRepository.findAll()
                .stream()
                .map(awardMapper::toDto)
                .toList();
    }

    @Override
    public Page<AwardDto> getAllAwardPage(PageRequestCustom pageRequestCustom) {
        PageRequestCustom validated = pageCustomHelpper.validatePageCustom(pageRequestCustom);
        Pageable pageable = PageRequest.of(validated.getPageNumber() - 1, validated.getPageSize());
        return awardRepository.findAll(pageable).map(awardMapper::toDto);
    }

    @Override
    public List<AwardDto> getAwardByUser(String userId) {
        return awardRepository.findByUser_Id(userId)
                .stream()
                .map(awardMapper::toDto)
                .toList();
    }

    @Override
    public AwardDto getAwardById(Integer id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id award"));
        return awardMapper.toDto(award);
    }

    @Override
    public AwardDto createAward(AwardRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id user"));

        Award award = Award.builder()
                .user(user)
                .awardName(request.getAwardName())
                .organization(request.getOrganization())
                .date(request.getDate())
                .description(request.getDescription())
                .build();

        awardRepository.save(award);
        return awardMapper.toDto(award);
    }

    @Override
    public AwardDto updateAward(int id, AwardRequest request) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id award"));

        award.setAwardName(request.getAwardName() != null && !request.getAwardName().isEmpty()
                ? request.getAwardName()
                : award.getAwardName());

        award.setOrganization(request.getOrganization() != null && !request.getOrganization().isEmpty()
                ? request.getOrganization()
                : award.getOrganization());

        award.setDate(request.getDate() != null ? request.getDate() : award.getDate());

        award.setDescription(request.getDescription() != null && !request.getDescription().isEmpty()
                ? request.getDescription()
                : award.getDescription());

        Award updated = awardRepository.save(award);
        return awardMapper.toDto(updated);
    }

    @Override
    public AwardDto deleteAward(int id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Khong tim thay id award"));
        awardRepository.delete(award);
        return awardMapper.toDto(award);
    }
}
