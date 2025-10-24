package com.project.it_job.controller;

import com.project.it_job.dto.ReportStatusDTO;
import com.project.it_job.entity.ReportStatus;
import com.project.it_job.mapper.ReportStatusMapper;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.ReportStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/report-status")
@CrossOrigin(origins = "*")
public class ReportStatusController {

    @Autowired
    private  ReportStatusService reportStatusService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        List<ReportStatus> list = reportStatusService.getAll();

        BaseResponse baseResponse = new BaseResponse();
        if(list.isEmpty()){
            baseResponse.setCode(HttpStatus.OK.value());
            baseResponse.setMessage("Report Status List is Empty");
            baseResponse.setData(list);
            return ResponseEntity.ok(baseResponse);
        }
        List<ReportStatusDTO> dtoList = list.stream()
                        .map(ReportStatusMapper::toDTO)
                        .collect(Collectors.toList());
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setMessage("Get list of Report Status is Success");
        baseResponse.setData(dtoList);
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable int id) {
        Optional<ReportStatus> optional = reportStatusService.getById(id);
        BaseResponse response = new BaseResponse();

        if (optional.isPresent()) {
            ReportStatusDTO dto = ReportStatusMapper.toDTO(optional.get());
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Get information of Report Status is Success");
            response.setData(dto);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Not found Status have id: " + id);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody ReportStatusDTO dto) {
        ReportStatus entity = ReportStatusMapper.toEntity(dto);
        ReportStatus created = reportStatusService.create(entity);
        ReportStatusDTO result = ReportStatusMapper.toDTO(created);

        BaseResponse response = new BaseResponse(
                HttpStatus.CREATED.value(),
                "Add Report Status is Success",
                result
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable int id, @RequestBody ReportStatusDTO dto) {
        ReportStatus entity = ReportStatusMapper.toEntity(dto);
        ReportStatus updated = reportStatusService.update(id, entity);
        BaseResponse response = new BaseResponse();
        if (updated != null) {
            ReportStatusDTO result = ReportStatusMapper.toDTO(updated);
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Updated status is Success");
            response.setData(result);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Not found status have id: " + id);
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable int id) {
        boolean deleted = reportStatusService.delete(id);
        BaseResponse response = new BaseResponse();

        if (deleted) {
            response.setCode(HttpStatus.OK.value());
            response.setMessage("Delete Report Status Success");
            response.setData(null);
            return ResponseEntity.ok(response);
        } else {
            response.setCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Report Status with id " + id + " not found");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
