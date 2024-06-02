package com.jackfordtech.plantpix.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PlantPicController {
    @GetMapping(value="/helloworld")
    public String helloWorld() {
        return "Hello world!";
    }
}