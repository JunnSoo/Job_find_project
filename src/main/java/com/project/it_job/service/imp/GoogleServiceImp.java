package com.project.it_job.service.imp;

import com.project.it_job.dto.GoogleInforDTO;
import com.project.it_job.dto.InforEmailDTO;
import com.project.it_job.entity.auth.Role;
import com.project.it_job.entity.auth.User;
import com.project.it_job.repository.auth.RoleRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.service.GoogleService;
import com.project.it_job.util.KafkaHelpper;
import com.project.it_job.util.TimeHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GoogleServiceImp implements GoogleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final KafkaHelpper kafkaHelpper;
    private final TimeHelpper timeHelpper;
    @Override
    public User getUsersLogin(GoogleInforDTO googleInforDTO) {
        User user = userRepository.findByEmail(googleInforDTO.getEmail())
                .orElse(null);

        if (user == null) {
    //            case không trong db
            Role role = roleRepository.findByRoleNameIgnoreCase("USER").orElseGet(
                    () -> roleRepository.save(Role.builder()
                            .roleName("USER")
                            .description("")
                            .updatedDate(LocalDateTime.now())
                            .createdDate(LocalDateTime.now())
                            .build())
            );
            User newUser = User.builder()
                    .email(googleInforDTO.getEmail())
                    .password("")
                    .firstName(googleInforDTO.getFirstName())
                    .lastName(googleInforDTO.getLastName())
                    .avatar(googleInforDTO.getPicture())
                    .role(role)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build();

            kafkaHelpper.sendKafkaEmailRegister("register_email",
                    InforEmailDTO.builder()
                            .email(newUser.getEmail())
                            .firstName(newUser.getFirstName())
                            .dateCreated(timeHelpper.parseLocalDateTimeToSimpleTime(newUser.getCreatedDate()))
                            .build());
            return userRepository.save(newUser);
        } else {
    //            case có trong db
            if (user.getFirstName().isEmpty() || user.getFirstName() == null) {
                user.setFirstName(googleInforDTO.getFirstName());
            }
            if (user.getLastName().isEmpty() || user.getLastName() == null) {
                user.setLastName(googleInforDTO.getLastName());
            }
            if (user.getAvatar().isEmpty() || user.getAvatar() == null) {
                user.setAvatar(googleInforDTO.getPicture());
            }
            return userRepository.save(user);
        }
    }
}
