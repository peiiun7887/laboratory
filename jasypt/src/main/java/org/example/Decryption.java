package org.example;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

public class Decryption {

    public static void main(String[] args) {
        String encryptedText = "K4hqVHzLs8GErPj2F0BOTellnmjsUBqe";
        String key = "mykey";
        String algorithm = "PBEWithMD5AndDES";


        System.out.println("Before decryption: " + encryptedText);
        System.out.println("After decryption: " + decryption(encryptedText, key, algorithm));

    }

    private static String decryption(String encryptedText, String key, String algorithm){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm(algorithm);
        config.setPassword(key);
        encryptor.setConfig(config);
        return encryptor.decrypt(encryptedText);
    }

}
