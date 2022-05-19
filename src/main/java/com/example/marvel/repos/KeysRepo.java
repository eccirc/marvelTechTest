package com.example.marvel.repos;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public class KeysRepo {

    @Value("${app.publicKey}")
    private String publicKey;
    @Value("${app.privateKey}")
    private String privateKey;
    @Value("${app.persistenceFile}")
    private String fileName;

    private long unixTime;

    public KeysRepo() {
        this.unixTime = Instant.now().getEpochSecond();
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getFileName() {
        return fileName;
    }

    public long getUnixTime() {
        return unixTime;
    }
}
