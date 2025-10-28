package com.project.it_job.controller.auth;

import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUpdateCompanyRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAllCompany(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(companyService.getAllCompany(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(companyService.getAllCompanyPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<?> getCompanyById(@PathVariable String idCompany){
        return ResponseEntity.ok(BaseResponse.success(companyService.getCompanyById(idCompany),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> saveCompany(@Valid @RequestBody SaveUpdateCompanyRequest saveUpdateCompanyRequest){
        return ResponseEntity.ok(BaseResponse.success(companyService.saveCompany(saveUpdateCompanyRequest),"OK"));
    }

    @PutMapping("/{idCompany}")
    public ResponseEntity<?> updateCompany(@PathVariable String idCompany, @Valid @RequestBody SaveUpdateCompanyRequest saveUpdateCompanyRequest){
        return ResponseEntity.ok(BaseResponse.success(companyService.updateCompany(idCompany, saveUpdateCompanyRequest),"OK"));
    }

    @DeleteMapping("/{idCompany}")
    public ResponseEntity<?> deleteCompany(@PathVariable String idCompany){
        return ResponseEntity.ok(BaseResponse.success(companyService.deleteCompany(idCompany),"OK"));
    }
}
