package com.backend.jwt.service.file.implement;


import com.backend.jwt.service.file.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@Slf4j
public class FIleServiceImplement implements FileService {


    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null;
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + "." + extension;
        String saveFilePath = filePath + saveFileName;

        try {
            file.transferTo(new File(saveFilePath));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        String url = fileUrl + saveFileName;
        log.info("file url :  {} ", url );
        return url;

    }

    @Override
    public Resource getImage(String fileName) {
        Resource resource = null;

        try {
            resource = new UrlResource("file:"+filePath+fileName);
            log.info("resource : {}", resource);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return resource;
    }
}
