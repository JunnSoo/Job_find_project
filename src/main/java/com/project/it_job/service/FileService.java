package com.project.it_job.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void saveFiles(MultipartFile file);
    Resource getFile(String fileName);
}
