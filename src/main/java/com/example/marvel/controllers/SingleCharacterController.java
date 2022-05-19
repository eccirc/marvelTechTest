package com.example.marvel.controllers;


import com.example.marvel.services.SingleCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingleCharacterController {

    @Autowired
    SingleCharacterService service;

    @GetMapping("characters/{id}")
    private ResponseEntity<Object> singleCharacter(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).header( "Attribution:", "Data provided by Marvel. © 2014 Marvel").body(service.getSingleCharacterFromAPI(id));
        } catch (Error e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found, check your credentials.");
        }
    }

    @GetMapping("characters/{id}/{lang}")
    private ResponseEntity<Object> translatedCharacter(@PathVariable Integer id, @PathVariable String lang){
        try {
            return ResponseEntity.status(HttpStatus.OK).header( "Attribution:", "Data provided by Marvel. © 2014 Marvel").body(service.MarvelCharacterTranslated(id, lang));

        } catch (Error e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found, check your credentials, or that you have used a valid country code." +
                    "See https://www.iban.com/country-codes for details.");
        }
    }
}
