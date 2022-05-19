package com.example.marvel.controllers;


import com.example.marvel.services.SingleCharacterService;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.cloud.translate.TranslateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class SingleCharacterController {

    @Autowired
    SingleCharacterService service;

    @GetMapping("characters/{id}")
    private ResponseEntity<Object> singleCharacter(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).header( "Attribution:", "Data provided by Marvel. © 2014 Marvel").body(service.getSingleCharacterFromAPI(id));
        } catch (HttpClientErrorException.NotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found - possible invalid character code.");
        } catch (HttpClientErrorException.Unauthorized e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error fetching data - bad credentials. Contact repository owner.");
        }
    }

    @GetMapping("characters/{id}/{lang}")
    private ResponseEntity<Object> translatedCharacter(@PathVariable Integer id, @PathVariable String lang){
        ResponseEntity<Object> errorResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found, check your credentials or that you have used a valid country code. " +
                "See https://www.labnol.org/code/19899-google-translate-languages for details.");
        try {
            return ResponseEntity.status(HttpStatus.OK).header( "Attribution:", "Data provided by Marvel. © 2014 Marvel")
                    .body(service.MarvelCharacterTranslated(id, lang));
        } catch (TranslateException e){
            return errorResponse;
        }

    }
}
