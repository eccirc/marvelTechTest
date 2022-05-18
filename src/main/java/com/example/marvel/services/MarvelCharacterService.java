package com.example.marvel.services;
import com.example.marvel.models.MarvelCharacter;
import com.example.marvel.utilities.JSONUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;

@Service
public class MarvelCharacterService {

    @Value("${app.publicKey}")
    private String publicKey;
    @Value("${app.privateKey}")
    private String privateKey;

    @Value("${app.persistenceFile}")
    private String fileName;
    private long unixTime;

    private Object ids;

    private final ObjectMapper mapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    public MarvelCharacterService() {
        this.unixTime = Instant.now().getEpochSecond();
    }

    public void setIds(){
        if(getDataFromPersistenceFile() != null){
            ids = getDataFromPersistenceFile();
        } else ids = getFromApiALt();
    }

    private String hashBuilder(long timeStamp, String privateKey, String publicKey){
        return DigestUtils.md5Hex(timeStamp + privateKey + publicKey);
    }

    public Object getDataFromPersistenceFile(){
        return JSONUtils.readFile(fileName);
    }
    public Object getSingleCharacterFromAPI(Integer characterID){
        String hash = hashBuilder(unixTime, privateKey, publicKey);
       String url = "https://gateway.marvel.com:443/v1/public/characters/" + characterID + "?ts=" + unixTime + "&apikey=" + publicKey + "&hash=" + hash;
       JsonNode node = restTemplate.getForEntity(url, JsonNode.class).getBody();
       assert node != null;
        ArrayNode data = (ArrayNode) node.get("data").get("results");
        Object character = new Object();
        for (JsonNode details : data) {
            int id = Integer.parseInt(details.get("id").asText());
            String name = details.get("name").asText();
            String description = details.get("description").asText();
            String path = details.get("thumbnail").get("path").asText();
            String extension = details.get("thumbnail").get("extension").asText();
            character = new MarvelCharacter(id, name, description, path, extension);
        }
        return character;
    }

    public Object getFromApiALt(){
        int offset = 0;
        JSONArray results = new JSONArray();
        String hash = hashBuilder(unixTime, privateKey, publicKey);
        String url =  "http://gateway.marvel.com/v1/public/characters?ts=" + unixTime + "&apikey=" + publicKey + "&hash=" + hash + "&limit=100" + "&offset=";
        while(offset < 15){
            JsonNode data = restTemplate.getForEntity(url + (offset * 100), JsonNode.class).getBody();
            results.addAll(data.get("data").get("results").findValues("id"));
            offset ++;
        }
        JSONUtils.persistData(results, fileName);
        return results;
    }

    public Object getIds() {
        return ids;
    }
}
