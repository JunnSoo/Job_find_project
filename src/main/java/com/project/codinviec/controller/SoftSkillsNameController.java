package com.project.codinviec.controller;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.SaveUpdateSoftSkillNameRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.SoftSkillsNameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/soft-skills-name")
@RequiredArgsConstructor
public class SoftSkillsNameController {
    private final SoftSkillsNameService softSkillsNameService;

    @GetMapping
    public ResponseEntity<?> getAllSoftSkillsName(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.getAllSoftSkillsName(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.getAllSoftSkillsNamePage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idSoftSkillName}")
    public ResponseEntity<?> getSoftSkillsNameById(@PathVariable Integer idSoftSkillName){
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.getSoftSkillsNameById(idSoftSkillName),"OK"));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getSoftSkillsNameByUserId(@PathVariable String idUser){
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.getSoftSkillsNameByUserId(idUser),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveSoftSkillsName(@Valid @RequestBody SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest){
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.saveSoftSkillsName(saveUpdateSoftSkillNameRequest),"OK"));
    }

    @PutMapping("/{idSoftSkillName}")
    public ResponseEntity<?> updateSoftSkillsName(@PathVariable Integer idSoftSkillName,@Valid @RequestBody SaveUpdateSoftSkillNameRequest saveUpdateSoftSkillNameRequest){
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.updateSoftSkillsName( idSoftSkillName,saveUpdateSoftSkillNameRequest),"OK"));
    }

    @DeleteMapping("/{idSoftSkillName}")
    public ResponseEntity<?> deleteSoftSkillsName(@PathVariable Integer idSoftSkillName){
        return ResponseEntity.ok(BaseResponse.success(softSkillsNameService.deleteSoftSkillsNameById(idSoftSkillName),"OK"));
    }


}
