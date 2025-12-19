package com.project.codinviec.service.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFiles(MultipartFile file);

    Resource getFile(String fileName);

    void deleteFile(String fileName);
}
