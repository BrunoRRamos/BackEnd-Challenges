package com.desafios_backend.criptography;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAUtils {

    public static final String ALGORITHM = "RSA";
    public static final String PATH_PUBLIC_KEY = "./src/main/resources/keys/public.key/";
    public static final String PATH_PRIVATE_KEY = "./src/main/resources/keys/private.key/";

    private static void createFiles(File publicKeyFile, File privateKeyFile) throws IOException {
        if (publicKeyFile.getParentFile() != null) {
            publicKeyFile.getParentFile().mkdirs();
        }
        publicKeyFile.createNewFile();

        if (privateKeyFile.getParentFile() != null) {
            privateKeyFile.getParentFile().mkdirs();
        }
        privateKeyFile.createNewFile();
    }

    private static void saveKeyPair(File publicKeyFile, File privateKeyFile, KeyPair keyPair) throws IOException {
        ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                new FileOutputStream(publicKeyFile)
        );
        publicKeyOS.writeObject(keyPair.getPublic());
        publicKeyOS.close();

        ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                new FileOutputStream(privateKeyFile)
        );
        privateKeyOS.writeObject(keyPair.getPrivate());
        privateKeyOS.close();
    }

    public static void keyPairGen() {
        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            File publicKeyFile = new File(PATH_PUBLIC_KEY);
            File privateKeyFile = new File(PATH_PRIVATE_KEY);

            createFiles(publicKeyFile, privateKeyFile);
            saveKeyPair(publicKeyFile, privateKeyFile, keyPair);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decryptedData);
    }
}
