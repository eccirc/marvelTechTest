package com.example.marvel.services;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.cloud.translate.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TranslateText {
    @Value("${app.gcpKey}")
    private String key;

    public TranslateText() {
    }

    public String textTranslate(String inputText, String lang) throws TranslateException{
        Translate translate = TranslateOptions
                .newBuilder()
                .setApiKey(key)
                .setTargetLanguage(lang)
                .build()
                .getService();
            Translation translation = translate.translate(inputText);
            return translation.getTranslatedText();


    }
}
