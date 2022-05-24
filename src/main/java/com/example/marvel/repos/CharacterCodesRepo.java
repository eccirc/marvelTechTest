package com.example.marvel.repos;

import com.example.marvel.utilities.HashBuilder;
import com.example.marvel.utilities.JSONUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CharacterCodesRepo {

    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    KeysRepo keys;

    private Object ids;

    public Object getDataFromPersistenceFile(){
        return JSONUtils.readFile(keys.getFileName());
    }

    public Object getFromApiALt(){
        int offset = 0;
        JSONArray results = new JSONArray();
        String hash = HashBuilder.hash(keys.getUnixTime(), keys.getPrivateKey(), keys.getPublicKey());
        String url =  "http://gateway.marvel.com/v1/public/characters?ts=" + keys.getUnixTime()
                + "&apikey=" + keys.getPublicKey()
                + "&hash=" + hash
                + "&limit=100" + "&offset=";
        while(offset < 15){
            JsonNode data = restTemplate.getForEntity(url + (offset * 100), JsonNode.class).getBody();
            results.addAll(data.get("data").get("results").findValues("id"));
            offset ++;
        }
        JSONUtils.persistData(results, keys.getFileName());
        return results;
    }
    public Object getIds() {
        return ids;
    }

    public void setIds(){
        if(getDataFromPersistenceFile() != null){
            ids = getDataFromPersistenceFile();
        } else ids = getFromApiALt();
    }


}
