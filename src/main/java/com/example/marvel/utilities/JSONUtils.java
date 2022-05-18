package com.example.marvel.utilities;

import com.example.marvel.models.MarvelCharacter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JSONUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    private static ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    public static void persistData(Object o, String fileName){
        try {
            File file = new File(fileName);
            if(!file.exists() || file.length() == 0){
                writer.writeValue(Paths.get(fileName).toFile(), o);
            }
            else {
                System.out.println("File already exists, read from this file instead");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object readFile(String fileName){
        try {
            String filePath = Files.readString(Path.of(fileName));
            return mapper.readValue(filePath, new TypeReference<Object>() {
            });

        } catch (IOException e){
            return null;
        }
    }
}
