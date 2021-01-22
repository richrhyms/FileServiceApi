package com.richrhyms.fileserviceapi.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UploadFileResponse {
    private final String fileName;
    private final String fileDownloadUri;
    private final String fileType;
    private final long size;

//    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
//        this.fileName = fileName;
//        this.fileDownloadUri = fileDownloadUri;
//        this.fileType = fileType;
//        this.size = size;
//    }
}
