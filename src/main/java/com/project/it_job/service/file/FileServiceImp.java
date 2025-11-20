package com.project.it_job.service.file;

import com.project.it_job.exception.file.FileExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    @Value("${upload.image}")
    private String root;

    @Override
    public String saveFiles(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new FileExceptionHandler();
            }

            Path rootPath = Paths.get(root);
            if(Files.notExists(rootPath)){
                Files.createDirectories(rootPath);
            }

            // Tạo tên file unique: timestamp_uuid_originalFilename
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String uniqueId = UUID.randomUUID().toString().substring(0, 8);
            String uniqueFilename = timestamp + "_" + uniqueId + "_" + originalFilename + extension;

            Path filePath = rootPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFilename;
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
