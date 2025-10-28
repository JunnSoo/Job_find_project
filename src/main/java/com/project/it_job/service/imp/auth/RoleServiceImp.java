package com.project.it_job.service.imp.auth;

import com.project.it_job.dto.auth.RoleDTO;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.mapper.auth.RoleMapper;
import com.project.it_job.repository.auth.RoleRepository;
import com.project.it_job.request.auth.RoleRequest;
import com.project.it_job.service.auth.RoleService;
import com.project.it_job.specification.auth.RoleSpecification;
import com.project.it_job.util.PageCustomHelpper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PageCustomHelpper pageCustomHelpper;

    public RoleServiceImp(RoleRepository roleRepository, RoleMapper roleMapper, PageCustomHelpper pageCustomHelpper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.pageCustomHelpper = pageCustomHelpper;
    }

    @Override
    public List<RoleDTO> getAll() {
        return roleMapper.toRoleDTOList(roleRepository.findAll());
    }

    @Override
    public Page<RoleDTO> getAllWithPage(PageRequestCustom req) {

        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(req);
        //Search
        Specification<Role> spec = RoleSpecification.searchByName(req.getKeyword());

        //Sort
        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "roleNameAsc" -> Sort.by(Sort.Direction.ASC, "roleName");
            case "roleNameDesc" -> Sort.by(Sort.Direction.DESC, "roleName");
            case "descriptionAsc" -> Sort.by(Sort.Direction.ASC, "description");
            case "descriptionDesc" -> Sort.by(Sort.Direction.DESC, "description");
            case "createdDateDesc" -> Sort.by(Sort.Direction.DESC, "createdDate");
            case "updatedDateAsc" -> Sort.by(Sort.Direction.ASC, "updatedDate");
            case "updatedDateDesc" -> Sort.by(Sort.Direction.DESC, "updatedDate");
            default -> Sort.by(Sort.Direction.ASC, "createdDate");
        };

        //Page
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return roleRepository.findAll(spec,pageable)
                .map(roleMapper::toRoleDTO);
    }

    @Override
    public RoleDTO getById(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy role Id"));
        return roleMapper.toRoleDTO(role);
    }

    @Override
    @Transactional
    public RoleDTO create(RoleRequest req) {
        Role existingRole = roleRepository.findRoleByRoleNameIgnoreCase(req.getRoleName());

        if(existingRole != null){
            throw new ConflictException("Role đã tồn tại");
        }

        Role role = roleMapper.toCreateRole(req);
        return roleMapper.toRoleDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO update(String id,RoleRequest req) {
        Role existingRole = roleRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy role Id"));
        roleMapper.toUpdateRole(req, existingRole);
        return roleMapper.toRoleDTO(roleRepository.save(existingRole));
    }


    @Override
    public RoleDTO deleteById(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy role ID"));
        roleRepository.delete(role);
        return roleMapper.toRoleDTO(role);
    }
}
