package com.richrhyms.fileserviceapi.controller;

import com.richrhyms.fileserviceapi.domain.UploadFileResponse;
import com.richrhyms.fileserviceapi.service.FileStorageService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

        @Inject
        private FileStorageService fileStorageService;

        @PostMapping("upload")
        public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
            String fileName = fileStorageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("file/download/")
                    .path(fileName)
                    .toUriString();

            return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
        }

        @GetMapping("/download/{fileName:.+}")
        public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
            // Load file as Resource
            Resource resource = fileStorageService.loadFileAsResource(fileName);

            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("Could not determine file type.");
//                logger.debug("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined
            if(contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }

}
