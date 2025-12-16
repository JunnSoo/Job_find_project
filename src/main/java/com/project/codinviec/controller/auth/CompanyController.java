package com.project.codinviec.controller.auth;

import com.project.codinviec.request.PageRequestCompany;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.SaveUpdateCompanyRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.CompanyService;
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
    public ResponseEntity<?> getAllCompany(PageRequestCompany pageRequestCompany){
        if (pageRequestCompany.getPageNumber() == 0 && pageRequestCompany.getPageSize() == 0  && pageRequestCompany.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(companyService.getAllCompany(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(companyService.getAllCompanyPage(pageRequestCompany),"OK"));
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
