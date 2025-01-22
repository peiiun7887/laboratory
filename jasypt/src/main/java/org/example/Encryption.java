package org.example;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

public class Encryption {

    public static void main(String[] args) {
        String plainText = "test1234";
        String key = "mykey";
        String algorithm = "PBEWithMD5AndDES";

        System.out.println("Before encryption: " + plainText);
        System.out.println("After encryption: " + encryption(plainText, key, algorithm));

    }

    private static String encryption(String plainText, String key, String algorithm){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm(algorithm);
        config.setPassword(key);
        encryptor.setConfig(config);
        return encryptor.encrypt(plainText);
    }
}
