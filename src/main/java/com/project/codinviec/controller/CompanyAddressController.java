package com.project.codinviec.controller;

import com.project.codinviec.request.CompanyAddressRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.CompanyAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company-address")
@RequiredArgsConstructor
public class CompanyAddressController {
    private final CompanyAddressService companyAddressService;

    @GetMapping
    public ResponseEntity<?> getAllCompanyAddresses(PageRequestCustom pageRequestCustom) {
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(companyAddressService.getAllCompanyAddresses(),"OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(companyAddressService.getAllCompanyAddressesPage(pageRequestCustom),"OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyAddressById(@PathVariable("id") int id){
        return ResponseEntity.ok(BaseResponse.success(companyAddressService.getCompanyAddressById(id),"OK"));
    }

    @PostMapping
    public ResponseEntity<?> addCompanyAddress(@RequestBody CompanyAddressRequest companyAddressRequest){
        return ResponseEntity.ok(BaseResponse.success(companyAddressService.saveCompanyAddress(companyAddressRequest),"OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompanyAddress(@PathVariable("id") int id, @RequestBody CompanyAddressRequest companyAddressRequest){
        return ResponseEntity.ok(BaseResponse.success(companyAddressService.updateCompanyAddress(id,companyAddressRequest),"OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompanyAddress(@PathVariable("id") int id){
        return ResponseEntity.ok(BaseResponse.success(companyAddressService.deleteCompanyAddress(id),"OK"));
    }
}
