package com.arcadag.test.dto;

import java.time.LocalDateTime;

public class FTPFileDTO {
    private String absolutePath;
    private LocalDateTime created;
    private Long size;

    public FTPFileDTO(String absolutePath, LocalDateTime created, Long size) {
        this.absolutePath = absolutePath;
        this.created = created;
        this.size = size;
    }

    public FTPFileDTO() {
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
