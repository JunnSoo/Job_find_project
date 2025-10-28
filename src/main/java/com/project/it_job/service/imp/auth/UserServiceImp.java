package com.project.it_job.service.imp.auth;

import com.project.it_job.dto.auth.UserDTO;
import com.project.it_job.entity.auth.Company;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.ConflictException;
import com.project.it_job.exception.EmailAlreadyExists;
import com.project.it_job.exception.EmailNotChangeExceptionHandler;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.mapper.auth.UserMapper;
import com.project.it_job.repository.auth.CompanyRepository;
import com.project.it_job.repository.auth.RoleRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.request.auth.SaveUpdateUserRequest;
import com.project.it_job.service.auth.UserService;
import com.project.it_job.specification.auth.UserSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PageCustomHelpper  pageCustomHelpper;
    private final UserSpecification userSpecification;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(u-> userMapper.userToUserDTO(u)).toList();
    }
    @Override
    public Page<UserDTO> getAllUsersPage(PageRequestCustom pageRequestCustom) {
        //        validate pageCustom
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(pageRequestCustom);

//        Tạo page cho api
        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber(),pageRequestValidate.getPageSize());

//        Tạo search
        Specification<User> spec = Specification.allOf(
                userSpecification.searchByName(pageRequestValidate.getKeyword()));
        return userRepository.findAll(spec,pageable ).map(u-> userMapper.userToUserDTO(u));
    }

    @Override
    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new NotFoundIdExceptionHandler("Không tìm thấy id user")
        );
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO saveUser(SaveUpdateUserRequest saveUpdateUserRequest) {
//        check role
        Role role = null;
        if (saveUpdateUserRequest.getRoleId() != null && !saveUpdateUserRequest.getRoleId().isEmpty()) {
            role = roleRepository.findById(saveUpdateUserRequest.getRoleId()).orElseThrow(
                    ()-> new NotFoundIdExceptionHandler("Không tìm thấy id role!")
            );
        }
//      check company
        Company company = null;
        if (saveUpdateUserRequest.getCompanyId() != null && !saveUpdateUserRequest.getCompanyId().isEmpty()) {
            company = companyRepository.findById(saveUpdateUserRequest.getCompanyId()).orElseThrow(
                    ()-> new NotFoundIdExceptionHandler("Không tìm thấy id company!")
            );
        }

        User userCheckEmail = userRepository.findByEmail(saveUpdateUserRequest.getEmail()).orElse(null);
        if (userCheckEmail != null) {
            throw new EmailAlreadyExists("Email đã tồn tại!");
        }

        try {
            User user = userMapper.saveUserMapper(role,company,saveUpdateUserRequest);
            System.out.println(user.getBirthDate());

            return userMapper.userToUserDTO(userRepository.save(user));
        } catch (Exception e){
            throw new ConflictException("Lỗi thêm user!");
        }
    }

    @Override
    public UserDTO updateUser(String idUser, SaveUpdateUserRequest saveUpdateUserRequest) {
        User user = userRepository.findById(idUser).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy id user!")
        );

        if (!saveUpdateUserRequest.getEmail().equalsIgnoreCase(user.getEmail())) {
            throw new EmailNotChangeExceptionHandler("Không được thay đổi email!");
        }

        Role role = null;
        if (saveUpdateUserRequest.getRoleId() != null && !saveUpdateUserRequest.getRoleId().isEmpty()) {
            role = roleRepository.findById(saveUpdateUserRequest.getRoleId()).orElseThrow(
                    ()-> new NotFoundIdExceptionHandler("Không tìm thấy id role!")
            );
        }

        Company company = null;
        if (saveUpdateUserRequest.getCompanyId() != null && !saveUpdateUserRequest.getCompanyId().isEmpty()) {
            company = companyRepository.findById(saveUpdateUserRequest.getCompanyId()).orElseThrow(
                    ()-> new NotFoundIdExceptionHandler("Không tìm thấy id company!")
            );
        }

        try {
            User mappedUser = userMapper.updateUserMapper(idUser,role,company,saveUpdateUserRequest);
            mappedUser.setCreatedDate(user.getCreatedDate());
            return userMapper.userToUserDTO(userRepository.save(mappedUser));
        } catch (Exception e){
            e.printStackTrace();
            throw new ConflictException("Lỗi cập nhật user!");
        }
    }

    @Override
    public UserDTO deleteUser(String idUser) {
        User user = userRepository.findById(idUser).orElseThrow(
                ()-> new NotFoundIdExceptionHandler("Không tìm thấy id user!")
        );
        userRepository.delete(user);
        return userMapper.userToUserDTO(user);
    }
}
