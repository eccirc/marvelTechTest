package com.example.marvel.controllers;
import com.example.marvel.services.MarvelCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class MarvelController {

   @Autowired
   MarvelCharacterService service;

    @GetMapping("/test")
    private String test() {
        return "Hello, seeker";
    }

    @GetMapping("/characters")
    private ResponseEntity<Object> characters(){
        service.setIds();
       return ResponseEntity.status(HttpStatus.OK).body(service.getIds());
    }

    @GetMapping("characters/{id}")
    private ResponseEntity<Object> singleCharacter(@PathVariable Integer id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getSingleCharacterFromAPI(id));
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
