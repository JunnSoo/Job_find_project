package com.project.codinviec.service.imp;

import com.project.codinviec.dto.SoftSkillsNameDTO;
import com.project.codinviec.entity.SoftSkillsName;
import com.project.codinviec.entity.auth.User;
import com.project.codinviec.exception.common.NotFoundIdExceptionHandler;
import com.project.codinviec.mapper.SoftSkillsNameMapper;
import com.project.codinviec.repository.SoftSkillsNameRepository;
import com.project.codinviec.repository.auth.UserRepository;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateSoftSkillNameRequest;
import com.project.codinviec.service.SoftSkillsNameService;
import com.project.codinviec.specification.SoftSkillsNameSpecification;
import com.project.codinviec.util.helper.PageCustomHelper;
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
    private final PageCustomHelper pageCustomHelper;
    private final SoftSkillsNameSpecification softSkillsNameSpecification;

    private final UserRepository userRepository;


    @Override
    public List<SoftSkillsNameDTO> getAllSoftSkillsName() {
        return softSkillsNameRepository.findAll().stream().map(softSkillsNameMapper::softSkillsNameToSoftSkillsNameDTO).toList();
    }

    @Override
    public Page<SoftSkillsNameDTO> getAllSoftSkillsNamePage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1,pageRequestValidate.getPageSize());

//        Tạo search
        Specification<SoftSkillsName> spec = Specification.allOf(softSkillsNameSpecification.searchByName(pageRequestValidate.getKeyword()));
        return softSkillsNameRepository.findAll(spec, pageable).map(softSkillsNameMapper::softSkillsNameToSoftSkillsNameDTO);
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
                .stream().map(softSkillsNameMapper::softSkillsNameToSoftSkillsNameDTO).toList();
    }
}
