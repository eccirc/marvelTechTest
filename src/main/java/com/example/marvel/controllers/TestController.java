package com.example.marvel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {

    @GetMapping("/test")
    private String test() {
        return ("Hello Marvel fans!");
    }





}
