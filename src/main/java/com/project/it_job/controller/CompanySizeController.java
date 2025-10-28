package com.project.it_job.controller;


import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.SaveUpdateCompanySizeRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CompanySizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company-size")
@RequiredArgsConstructor
public class CompanySizeController {
    private final CompanySizeService companySizeService;

    @GetMapping
    public ResponseEntity<?> getAllCompanySize(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(companySizeService.getAllCompany(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(companySizeService.getAllCompanyPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idCompanySize}")
    public ResponseEntity<?> getCompanySize(@PathVariable int idCompanySize){
        return ResponseEntity.ok(BaseResponse.success(companySizeService.getCompanyById(idCompanySize),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveCompanySize(@Valid @RequestBody SaveUpdateCompanySizeRequest  saveUpdateCompanySizeRequest){
        return ResponseEntity.ok(BaseResponse.success(companySizeService.saveCompanySize(saveUpdateCompanySizeRequest),"OK"));
    }

    @PutMapping("/{idCompanySize}")
    public ResponseEntity<?> updateCompanySize(@PathVariable int idCompanySize, @Valid @RequestBody SaveUpdateCompanySizeRequest saveUpdateCompanySizeRequest){
        return ResponseEntity.ok(BaseResponse.success(companySizeService.updateCompanySize(idCompanySize,saveUpdateCompanySizeRequest),"OK"));
    }

    @DeleteMapping("/{idCompanySize}")
    public ResponseEntity<?> deleteCompanySize(@PathVariable int idCompanySize){
        return ResponseEntity.ok(BaseResponse.success(companySizeService.deleteCompanySize(idCompanySize),"OK"));
    }
}
