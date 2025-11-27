package com.project.codinviec.controller.auth;

import com.project.codinviec.request.PageRequestCustom;
import com.project.codinviec.request.auth.RoleRequest;
import com.project.codinviec.response.BaseResponse;
import com.project.codinviec.service.auth.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(PageRequestCustom pageRequestCustom) {

        if (pageRequestCustom.getPageNumber() == 0 && pageRequestCustom.getPageSize() == 0  && pageRequestCustom.getKeyword() == null ) {
            return ResponseEntity.ok(BaseResponse.success(roleService.getAll(),"OK"));
        }

        return ResponseEntity.ok(BaseResponse.success(roleService.getAllWithPage(pageRequestCustom),"OK"));
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
