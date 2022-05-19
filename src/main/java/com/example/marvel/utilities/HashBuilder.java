package com.example.marvel.utilities;

import org.apache.commons.codec.digest.DigestUtils;

public class HashBuilder {

    public static String hash(long timeStamp, String privateKey, String publicKey){
        return DigestUtils.md5Hex(timeStamp + privateKey + publicKey);
    }

}
