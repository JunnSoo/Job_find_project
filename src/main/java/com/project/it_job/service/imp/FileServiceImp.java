package com.project.it_job.service.imp;

import com.project.it_job.exception.FileExceptionHandler;
import com.project.it_job.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImp implements FileService {
    @Value("${upload.image}")
    private String root;

    @Override
    public void saveFiles(MultipartFile file) {
        try {
            Path rootPath = Paths.get(root);
            if(Files.notExists(rootPath)){
                Files.createDirectories(rootPath);
            }
            Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new FileExceptionHandler();
        }
    }

    @Override
    public Resource getFile(String fileName) {
        try {
            Path rootPath = Paths.get(root).resolve(fileName);
            Resource resource = new UrlResource(rootPath.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else {
                throw new FileExceptionHandler();
            }
        }
        catch (Exception e) {
            throw new FileExceptionHandler();
        }
    }
}
