package com.project.it_job.controller;

import com.project.it_job.entity.Industry;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/industry")
@CrossOrigin(origins = "*")
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<Industry> list = industryService.getAll();
        BaseResponse res = new BaseResponse();
        res.setCode(HttpStatus.OK.value());
        res.setMessage(list.isEmpty() ? "Industry list is empty" : "Get industry list successfully");
        res.setData(list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<Industry> optional = industryService.getById(id);
        BaseResponse res = new BaseResponse();
        if (optional.isPresent()) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Get industry successfully");
            res.setData(optional.get());
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Industry not found with id: " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody Industry industry) {
        Industry created = industryService.create(industry);
        BaseResponse res = new BaseResponse(HttpStatus.CREATED.value(), "Create industry successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody Industry industry) {
        Industry updated = industryService.update(id, industry);
        BaseResponse res = new BaseResponse();
        if (updated != null) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Update industry successfully");
            res.setData(updated);
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Industry not found with id: " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = industryService.delete(id);
        BaseResponse res = new BaseResponse();
        if (deleted) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Delete industry successfully");
            res.setData(null);
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Industry not found with id: " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
}
