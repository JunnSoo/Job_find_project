package com.project.it_job.controller;

import com.project.it_job.dto.WardDTO;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ward")
@CrossOrigin(origins = "*")
public class WardController {
    @Autowired
    private WardService wardService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<WardDTO> list = wardService.getAll();
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),
                list.isEmpty() ? "Ward list is empty" : "Get list of wards success", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<WardDTO> optional = wardService.getById(id);
        if (optional.isPresent())
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Get ward success", optional.get()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Ward not found", null));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody WardDTO dto) {
        WardDTO created = wardService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse(HttpStatus.CREATED.value(), "Create ward success", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody WardDTO dto) {
        WardDTO updated = wardService.update(id, dto);
        if (updated != null)
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Update ward success", updated));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Ward not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = wardService.delete(id);
        if (deleted)
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), "Xóa phường/xã thành công", null));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy ward", null));
    }
}
