package com.project.it_job.controller;

import com.project.it_job.dto.ProvinceDTO;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/province")
@CrossOrigin(origins = "*")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<ProvinceDTO> list = provinceService.getAll();
        BaseResponse res = new BaseResponse(HttpStatus.OK.value(),
                list.isEmpty() ? "Province list is empty" : "Get list of provinces success", list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<ProvinceDTO> optional = provinceService.getById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Get province success", optional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Province not found with id: " + id, null));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ProvinceDTO dto) {
        ProvinceDTO created = provinceService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse(HttpStatus.CREATED.value(), "Create province success", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody ProvinceDTO dto) {
        ProvinceDTO updated = provinceService.update(id, dto);
        if (updated != null)
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Update province success", updated));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Province not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = provinceService.delete(id);
        if (deleted)
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Deleted province is success", null));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Not found Province", null));
    }
}
