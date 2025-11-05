package com.project.it_job.service.imp;

import com.project.it_job.dto.CVUserDTO;
import com.project.it_job.entity.CVUser;
import com.project.it_job.entity.auth.User;
import com.project.it_job.exception.NotFoundIdExceptionHandler;
import com.project.it_job.exception.UnauthorizedDeleteException;
import com.project.it_job.mapper.CVUserMapper;
import com.project.it_job.repository.CVUserRepository;
import com.project.it_job.repository.auth.UserRepository;
import com.project.it_job.request.CVUserRequest;
import com.project.it_job.request.PageRequestCustom;
import com.project.it_job.service.CVUserService;
import com.project.it_job.specification.CVUserSpecification;
import com.project.it_job.util.PageCustomHelpper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CVUserServiceImp implements CVUserService {

    private final CVUserRepository cvUserRepository;
    private final UserRepository userRepository;
    private final CVUserMapper cvUserMapper;
    private final PageCustomHelpper  pageCustomHelpper;
    private final CVUserSpecification cvUserSpecification;

    @Override
    public List<CVUserDTO> getAll() {
        return cvUserMapper.cvUserDTOListToCVUserDTOList(cvUserRepository.findAll());
    }

    @Override
    @Transactional
    public Page<CVUserDTO> getAllWithPage(PageRequestCustom req) {
        PageRequestCustom pageRequestValidate = pageCustomHelpper.validatePageCustom(req);

        Specification<CVUser> spec = cvUserSpecification.searchByTitle(pageRequestValidate.getKeyword());

        Sort sort = switch (pageRequestValidate.getSortBy()) {
            case "versionAsc" -> Sort.by(Sort.Direction.ASC, "version");
            case "versionDesc" -> Sort.by(Sort.Direction.DESC, "version");
            case "titleAsc" -> Sort.by(Sort.Direction.ASC, "title");
            case "titleDesc" -> Sort.by(Sort.Direction.DESC, "title");
            case "isActiveAsc" ->  Sort.by(Sort.Direction.ASC, "isActive");
            case "isActiveDesc" ->  Sort.by(Sort.Direction.DESC, "isActive");
            default -> Sort.by(Sort.Direction.ASC, "id");
        };

        Pageable pageable = PageRequest.of(pageRequestValidate.getPageNumber() - 1, pageRequestValidate.getPageSize(), sort);

        return cvUserRepository.findAll(spec,pageable)
                .map(cvUserMapper::toCVUserDTO);
    }

    @Override
    public CVUserDTO getById(Integer id) {
        CVUser cvUser = cvUserRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id CV User "));
        return cvUserMapper.toCVUserDTO(cvUser);
    }

    @Override
    @Transactional
    public CVUserDTO create(CVUserRequest req) {
        User candidate = userRepository.findById(req.getCandidateId())
                .orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id User"));

        Integer maxVerison = cvUserRepository.findFirstByCandidate_IdOrderByVersionDesc(req.getCandidateId())
                .map(cv -> cv.getVersion() + 1)
                .orElse(1); // nguoc lai neu chua cv nao thi set no la 1


        CVUser cvUser = cvUserMapper.toCreateCVUser(req,candidate);
        cvUser.setVersion(maxVerison);

        return cvUserMapper.toCVUserDTO(cvUserRepository.save(cvUser));
    }

    @Override
    @Transactional
    public CVUserDTO update(Integer id, CVUserRequest req) {
        CVUser cvUser = cvUserRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id CV User "));

        if (!cvUser.getCandidate().getId().equals(req.getCandidateId())) {
            throw new NotFoundIdExceptionHandler("CV không thuộc user này!");
        }

        CVUser mappedCVUser = cvUserMapper.toUpdateCVUser(cvUser,req);
        mappedCVUser.setCreatedAt(cvUser.getCreatedAt());
        return cvUserMapper.toCVUserDTO(cvUserRepository.save(mappedCVUser));
    }

    @Override
    public CVUserDTO deleteById(Integer id, String candidateId) {
        CVUser cvUser = cvUserRepository.findById(id).orElseThrow(() -> new NotFoundIdExceptionHandler("Không tìm thấy id CV User "));

        // sau này có jwt thì dựa vào token để xoá test logic la 9
        if(!cvUser.getCandidate().getId().equals(candidateId)) {
            throw new UnauthorizedDeleteException("Bạn không có quyền xóa CV này!");
        }

        cvUserRepository.delete(cvUser);
        return cvUserMapper.toCVUserDTO(cvUser);
    }
}
