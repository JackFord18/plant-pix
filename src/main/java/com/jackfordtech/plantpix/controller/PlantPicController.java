package com.jackfordtech.plantpix.controller;

import com.jackfordtech.plantpix.service.PlantPixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PlantPicController {
    @Autowired
    PlantPixService plantPixService;
    @PostMapping("/upload")
    public void upload( @RequestParam("image") MultipartFile file) throws IOException {
        plantPixService.uploadImage(file);
    }
}