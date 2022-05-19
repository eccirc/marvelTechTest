package com.example.marvel.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {

    @Value("${app.publicKey}")
    String pk;

    @GetMapping("/test")
    private String test() {
        return ("Hello Marvel fans!" + pk);
    }




}
