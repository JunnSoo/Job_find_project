package com.project.it_job.controller;

import com.project.it_job.dto.DegreeLevelDTO;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.DegreeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/degree-level")
@CrossOrigin(origins = "*")
public class DegreeLevelController {
    @Autowired
    private DegreeLevelService degreeLevelService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<DegreeLevelDTO> list = degreeLevelService.getAll();
        BaseResponse res = new BaseResponse(HttpStatus.OK.value(),
                list.isEmpty() ? "Danh sách rỗng" : "Lấy danh sách thành công",
                list);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<DegreeLevelDTO> optional = degreeLevelService.getById(id);
        BaseResponse res = new BaseResponse();
        if (optional.isPresent()) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Lấy thông tin thành công");
            res.setData(optional.get());
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Không tìm thấy degree level có id = " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody DegreeLevelDTO dto) {
        DegreeLevelDTO created = degreeLevelService.create(dto);
        BaseResponse res = new BaseResponse(HttpStatus.CREATED.value(), "Thêm thành công", created);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody DegreeLevelDTO dto) {
        DegreeLevelDTO updated = degreeLevelService.update(id, dto);
        BaseResponse res = new BaseResponse();
        if (updated != null) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Cập nhật thành công");
            res.setData(updated);
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Không tìm thấy degree level có id = " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = degreeLevelService.delete(id);
        BaseResponse res = new BaseResponse();
        if (deleted) {
            res.setCode(HttpStatus.OK.value());
            res.setMessage("Xóa thành công degree level có id = " + id);
            res.setData(null);
            return ResponseEntity.ok(res);
        } else {
            res.setCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Không tìm thấy degree level có id = " + id);
            res.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
    }
}
