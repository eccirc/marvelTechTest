package com.example.marvel.controllers;


import com.example.marvel.services.CharacterCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterCodeController {

    @Autowired
    CharacterCodeService service;

    @GetMapping("/characters")
    private ResponseEntity<Object> characterCodes(){
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .header( "Attribution:", "Data provided by Marvel. © 2014 Marvel")
                    .body(service.getCharacterCodes());
        } catch (Error e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error fetching resource");
        }
    }


}
