package com.jackfordtech.plantpix.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface PlantPixService {
    public void uploadImage(MultipartFile file) throws IOException;
    public byte[] latestImage();

}
