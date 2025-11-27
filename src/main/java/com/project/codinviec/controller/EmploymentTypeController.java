package com.project.codinviec.controller;

import com.project.codinviec.request.EmploymentTypeRequest;
import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.EmploymentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employment-type")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmploymentTypeController {
    private final EmploymentTypeService employmentTypeService;

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom){
        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0 && pageRequestCustom.getKeyword() == null) {
            return ResponseEntity.ok(BaseResponse.success(employmentTypeService.getAll(), "OK"));
        }
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.getAllWithPage(pageRequestCustom), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmploymentTypeRequest employmentType){
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.create(employmentType), "Tạo EmploymentType thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody EmploymentTypeRequest employmentType) {
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.update(id, employmentType), "Cập nhật Joblevel thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        employmentTypeService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa EmploymentType có ID " + id + " thành công"));
    }

}
