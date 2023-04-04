package com.arcadag.test.service;

import com.arcadag.test.converter.FTPFileConverter;
import com.arcadag.test.dto.FTPFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FTPService {
    @Value("${ftp.directory}")
    private String directory;

    @Value("${ftp.prefix}")
    private String prefix;
    private final FTPClient ftpClient;

    private final FTPFileConverter converter;
    @Autowired
    public FTPService(FTPClient ftpClient, FTPFileConverter converter) {
        this.ftpClient = ftpClient;
        this.converter = converter;
    }
    public List<FTPFileDTO> getFilesWithPrefix() throws IOException {
        List<FTPFileDTO> fileDtos = new ArrayList<>();
        List<FTPFile> ftpFiles = new ArrayList<>();
        searchFiles(ftpFiles);

        if (ftpFiles.size() != 0) {
            for (FTPFile ftpFile : ftpFiles) {
                FTPFileDTO fileDTO = converter.FTPFileToDTO(ftpFile);
                fileDtos.add(fileDTO);
            }
        }
        return fileDtos;
    }

    private void searchFiles(List<FTPFile> ftpFiles) throws IOException {
        log.info("check connect: {}", ftpClient.isConnected());
        ftpClient.changeWorkingDirectory(directory);
        FTPFile[] fileList = ftpClient.listFiles();

        if (fileList != null) {
            for (FTPFile file : fileList) {
                log.info("filename: {}", file.getName());
                if (file.isDirectory() && file.getName().equalsIgnoreCase("фотографии")) {
                    directory = file.getName();
                    searchFiles(ftpFiles);
                } else {
                    if (file.getName().toLowerCase().startsWith(prefix)) {
                        ftpFiles.add(file);
                    }
                }
            }
        }
    }
}
