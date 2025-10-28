package com.project.it_job.controller;

import com.project.it_job.entity.EmploymentType;
import com.project.it_job.request.EmploymentTypeRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.EmploymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employment-type")
@CrossOrigin(origins = "*")
public class EmploymentTypeController {
    @Autowired
    private EmploymentTypeService employmentTypeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.getAll(), "OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.getById(id), "OK"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmploymentTypeRequest employmentType){
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.create(employmentType), "Tạo EmploymentType thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EmploymentTypeRequest employmentType) {
        return ResponseEntity.ok(BaseResponse.success(employmentTypeService.update(id, employmentType), "Cập nhật Joblevel thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        employmentTypeService.delete(id);
        return ResponseEntity.ok(BaseResponse.success(null, "Xóa EmploymentType có ID " + id + " thành công"));
    }

}
