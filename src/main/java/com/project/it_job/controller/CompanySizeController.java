package com.project.it_job.controller;

import com.project.it_job.dto.CompanySizeDTO;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.CompanySizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company-size")
@CrossOrigin(origins = "*")
public class CompanySizeController {
    @Autowired
    private CompanySizeService companySizeService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<CompanySizeDTO> list = companySizeService.getAll();
        BaseResponse response = new BaseResponse(HttpStatus.OK.value(),
                list.isEmpty() ? "Company Size List is Empty" : "Get list of Company Size is Success",
                list);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<CompanySizeDTO> optional = companySizeService.getById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                    "Get information of Company Size is Success", optional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                            "Not found company size have id: " + id, null));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CompanySizeDTO dto) {
        CompanySizeDTO created = companySizeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse(HttpStatus.CREATED.value(),
                        "Add Company Size is Success", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody CompanySizeDTO dto) {
        CompanySizeDTO updated = companySizeService.update(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                    "Updated Company Size is Success", updated));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                            "Not found company size have id: " + id, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = companySizeService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                    "Deleted CompanySize is success", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse(HttpStatus.NOT_FOUND.value(),
                            "Not found company size have ID = " + id, null));
        }
    }
}
