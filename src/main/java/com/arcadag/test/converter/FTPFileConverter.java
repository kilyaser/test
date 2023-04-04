package com.arcadag.test.converter;

import com.arcadag.test.dto.FTPFileDTO;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class FTPFileConverter {
    public FTPFileDTO FTPFileToDTO(FTPFile file) {
        FTPFileDTO dto = new FTPFileDTO();
        dto.setAbsolutePath(file.getName());
        dto.setSize(file.getSize());
        dto.setCreated(LocalDateTime.ofInstant(file.getTimestamp().toInstant(), file.getTimestamp().getTimeZone().toZoneId()));
        return dto;
    }

}
