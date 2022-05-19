package com.example.marvel.services;


import com.example.marvel.models.MarvelCharacter;
import com.example.marvel.repos.KeysRepo;
import com.example.marvel.utilities.HashBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.cloud.translate.TranslateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class SingleCharacterService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    KeysRepo keys;

    @Autowired
    TranslateText trans;

    public MarvelCharacter getSingleCharacterFromAPI(Integer characterID) throws HttpClientErrorException.NotFound, HttpClientErrorException.Unauthorized {
        String hash = HashBuilder.hash(keys.getUnixTime(), keys.getPrivateKey(), keys.getPublicKey());
        String url = "https://gateway.marvel.com:443/v1/public/characters/" + characterID
                + "?ts=" + keys.getUnixTime()
                + "&apikey="
                + keys.getPublicKey()
                + "&hash="
                + hash;
        JsonNode node = restTemplate.getForEntity(url, JsonNode.class).getBody();
        assert node != null;
        ArrayNode data = (ArrayNode) node.get("data").get("results");
        MarvelCharacter character = null;
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

    public MarvelCharacter MarvelCharacterTranslated(Integer characterID, String langCode) throws TranslateException {
        MarvelCharacter mc = getSingleCharacterFromAPI(characterID);
        return new MarvelCharacter(mc.getId()
                , mc.getName()
                , trans.textTranslate(mc.getDescription(), langCode)
                , mc.getThumbnail().get("path")
                , mc.getThumbnail().get("extension") );
    }
}
