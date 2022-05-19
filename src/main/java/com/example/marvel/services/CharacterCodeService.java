package com.example.marvel.services;


import com.example.marvel.repos.CharacterCodesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterCodeService {

    @Autowired
    CharacterCodesRepo repo;

    public Object getCharacterCodes(){
        repo.setIds();
        return repo.getIds();
    }

}
