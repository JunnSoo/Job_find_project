package com.project.it_job.controller.auth;

import com.project.it_job.request.PageRequest;
import com.project.it_job.request.auth.RoleRequest;
import com.project.it_job.response.BaseResponse;
import com.project.it_job.service.auth.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(PageRequest pageRequest) {
        if(pageRequest.getPageSize() <= 0) {

            if(roleService.getAll() == null || roleService.getAll().isEmpty()) {
                return ResponseEntity.ok(
                        BaseResponse.success(null, "Danh sách role rỗng")
                );
            }
            return ResponseEntity.ok(
                    BaseResponse.success(roleService.getAll(),"Lấy danh sách role thành công")
            );
        }

        if(roleService.getAllWithPage(pageRequest) == null || roleService.getAllWithPage(pageRequest).isEmpty()) {
            return ResponseEntity.ok(
                    BaseResponse.success(null, "Danh sách role rỗng")
            );
        }

        return ResponseEntity.ok(
                BaseResponse.success(roleService.getAllWithPage(pageRequest), "Lấy danh sách role thành công")
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(BaseResponse.success(roleService.create(roleRequest), "Tạo role thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable String id) {
        return ResponseEntity.ok(BaseResponse.success(roleService.getById(id),"Lấy role thành công!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody RoleRequest roleRequest) {
        return ResponseEntity.ok(BaseResponse.success(roleService.update(id,roleRequest), "Cập nhật role thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(BaseResponse.success(roleService.deleteById(id),"Xoá role thành công "));
    }


}
