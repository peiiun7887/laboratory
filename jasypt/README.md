# Maven Run Configurations 
(Ref. [jasypt-spring-boot-starter](https://github.com/ulisesbocchio/jasypt-spring-boot/) )

---

## Encryption
- ### DEC(value) in application.properties
```
jasypt:encrypt -Djasypt.encryptor.password={password} -f pom.xml

```
- ### DEC(value) in custom file
```
jasypt:encrypt -Djasypt.encryptor.password={password} -Djasypt.plugin.path={file:src/main/resources/decrypt.properties} -f pom.xml

```

- ### in script
```
jasypt:encrypt-value -Djasypt.encryptor.password={password} -Djasypt.plugin.value={plainText} -f pom.xml
```


## Decryption
- ### ENC(value) in application.properties
```
jasypt:decrypt -Djasypt.encryptor.password={password} -f pom.xml

```
- ### ENC(value) in custom file
```
jasypt:decrypt -Djasypt.encryptor.password={password} -Djasypt.plugin.path=file:src/main/resources/decrypt.properties -f pom.xml

```

- ### in script
```
jasypt:decrypt-value -Djasypt.encryptor.password={password} -Djasypt.plugin.value={encryptedText} -f pom.xml
```

