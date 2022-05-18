package com.example.marvel.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;

public class MarvelCharacter {
    private int id;
    private String name;
    private String description;
    private Map<String, String> thumbnail = new HashMap<>();

    public MarvelCharacter(int id, String name, String description, String path, String extension) {
        this.id = id;
        this.name = name;
        this.description = description;
        setThumbnail(path, extension);
    }

    private void setThumbnail(String pathDetails, String extensionDetails){
        this.thumbnail.put("path", pathDetails);
        this.thumbnail.put("extension", extensionDetails);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getThumbnail() {
        return thumbnail;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
