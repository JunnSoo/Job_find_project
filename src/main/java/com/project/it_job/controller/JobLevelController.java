package com.project.it_job.controller;

import com.project.it_job.dto.JobLevelDTO;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-level")
@CrossOrigin(origins = "*")
public class JobLevelController {
    @Autowired
    private JobLevelService jobLevelService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<JobLevelDTO> list = jobLevelService.getAll();
        BaseResponse res = new BaseResponse(HttpStatus.OK.value(),
                "Get all job levels successfully", list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<JobLevelDTO> dto = jobLevelService.getById(id);
        BaseResponse res = new BaseResponse();
        if (dto.isPresent()) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Get job level successfully");
            res.setData(dto.get());
            return ResponseEntity.ok(res);
        }
        res.setCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Job level not found with id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody JobLevelDTO dto) {
        JobLevelDTO created = jobLevelService.create(dto);
        BaseResponse res = new BaseResponse(HttpStatus.CREATED.value(),
                "Create job level successfully", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody JobLevelDTO dto) {
        JobLevelDTO updated = jobLevelService.update(id, dto);
        BaseResponse res = new BaseResponse();
        if (updated != null) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Update job level successfully");
            res.setData(updated);
            return ResponseEntity.ok(res);
        }
        res.setCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Job level not found with id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = jobLevelService.delete(id);
        BaseResponse res = new BaseResponse();
        if (deleted) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Delete job level successfully");
            res.setData(null);
            return ResponseEntity.ok(res);
        }
        res.setCode(HttpStatus.NOT_FOUND.value());
        res.setMessage("Job level not found with id: " + id);
        res.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
