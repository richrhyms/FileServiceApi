package com.richrhyms.fileserviceapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Named;


public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
