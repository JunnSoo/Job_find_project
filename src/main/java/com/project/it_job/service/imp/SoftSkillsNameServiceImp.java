package com.project.it_job.service.imp;

import com.project.it_job.dto.SoftSkillsNameDTO;
import com.project.it_job.entity.SoftSkillsName;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.SoftSkillsNameMapper;
import com.project.it_job.repository.SoftSkillsNameRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateSoftSkillNameRequest;
import com.project.it_job.service.SoftSkillsNameService;
import com.project.it_job.specification.SoftSkillsNameSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftSkillsNameServiceImp implements SoftSkillsNameService {
    private final SoftSkillsNameRepository softSkillsNameRepository;
    private final SoftSkillsNameMapper  softSkillsNameMapper;
    private final PageCustomHelpper pageCustomHelpper;
    private final SoftSkillsNameSpecification softSkillsNameSpecification;

    private final UserRepository userRepository;


    @Override
    public List<SoftSkillsNameDTO> getAllSoftSkillsName() {
        return softSkillsNameRepository.findAll().stream().map(skn -> softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(skn)).toList();
    }

    @Override
    public Page<SoftSkillsNameDTO> getAllSoftSkillsNamePage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<SoftSkillsName> spec = Specification.allOf(softSkillsNameSpecification.searchByName(pageRequestValidate.getKeyword()));
        return softSkillsNameRepository.findAll(spec, pageable).map(skn -> softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(skn));
    }

    @Override
    public SoftSkillsNameDTO getSoftSkillsNameById(Integer idSoftSkillsName) {
        SoftSkillsName softSkillsName = softSkillsNameRepository.findById(idSoftSkillsName)
                .orElseThrow( ()->new NotFoundIdExceptionHandler("Không tìm thấy id soft skill name"));
        return softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(softSkillsName);
    }

    @Override
    public SoftSkillsNameDTO saveSoftSkillsName(SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest) {
        SoftSkillsName entity = softSkillsNameMapper.saveSoftSkillsName(saveUpdateSoftSkillNameRequest);
        SoftSkillsName saved = softSkillsNameRepository.save(entity);
        return softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(saved);
    }

    @Override
    @Transactional
    public SoftSkillsNameDTO updateSoftSkillsName(Integer idSoftSkillsName, SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest) {
        softSkillsNameRepository.findById(idSoftSkillsName)
                .orElseThrow( ()->new NotFoundIdExceptionHandler("Không tìm thấy id soft skill name"));
        SoftSkillsName entity = softSkillsNameMapper.updateSoftSkillsName(idSoftSkillsName, saveUpdateSoftSkillNameRequest);
        SoftSkillsName saved = softSkillsNameRepository.save(entity);
        return softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(saved);
    }

    @Override
    @Transactional
    public SoftSkillsNameDTO deleteSoftSkillsNameById(Integer idSoftSkillsName) {
        SoftSkillsName softSkillsName = softSkillsNameRepository.findById(idSoftSkillsName)
                .orElseThrow( ()->new NotFoundIdExceptionHandler("Không tìm thấy id soft skill name"));
        softSkillsNameRepository.deleteById(idSoftSkillsName);
        return softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(softSkillsName);
    }

    @Override
    public List<SoftSkillsNameDTO> getSoftSkillsNameByUserId(String idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(()-> new NotFoundIdExceptionHandler("Không tìm thấy id User"));
        return user.getListSoftSkillsName()
                .stream().map(skn -> softSkillsNameMapper.softSkillsNameToSoftSkillsNameDTO(skn)).toList();
    }
}
