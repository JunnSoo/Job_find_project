package com.project.it_job.controller;

import com.project.it_job.entity.EmploymentType;
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
    public ResponseEntity<BaseResponse> getAll(){
        List<EmploymentType> list = employmentTypeService.getAll();
        BaseResponse response = new BaseResponse();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(list.isEmpty() ? "Employment Type is Empty" : "Employment Type is Success");
        response.setData(list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id){
        Optional<EmploymentType> optional = employmentTypeService.getById(id);
        BaseResponse response = new BaseResponse();
        if (optional.isPresent()) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get employment Type successfully");
            response.setData(optional.get());
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(" employment Type with id " + id + " not found");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody EmploymentType employmentType){
        EmploymentType created = employmentTypeService.create(employmentType);
        BaseResponse response = new BaseResponse(HttpStatus.CREATED.value(), "Create Employment Type successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody EmploymentType employmentType) {
        EmploymentType updated = employmentTypeService.update(id, employmentType);
        BaseResponse response = new BaseResponse();
        if(updated != null){
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Update Employment Type successfully");
            response.setData(updated);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(" employment Type with id " + id + " not found");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = employmentTypeService.delete(id);
        BaseResponse response = new BaseResponse();
        if (deleted) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Delete Employment Type successfully");
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(" employment Type with id " + id + " not found");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
