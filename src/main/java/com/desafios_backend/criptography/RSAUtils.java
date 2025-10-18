package com.desafios_backend.criptography;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RSAUtils {

    public static final String ALGORITHM = "RSA";
    public static final String PATH_PUBLIC_KEY = "./src/main/resources/keys/public.key";
    public static final String PATH_PRIVATE_KEY = "./src/main/resources/keys/private.key";
    public static  final int KEY_SIZE = 1024;

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
        log.info("Public key created successfully");

        ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                new FileOutputStream(privateKeyFile)
        );
        privateKeyOS.writeObject(keyPair.getPrivate());
        privateKeyOS.close();
        log.info("Private key created successfully");
    }

    public static void keyPairGen() throws NoSuchAlgorithmException, IOException {
        log.info("Creating a new key pair");
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        File publicKeyFile = new File(PATH_PUBLIC_KEY);
        File privateKeyFile = new File(PATH_PRIVATE_KEY);

        createFiles(publicKeyFile, privateKeyFile);
        saveKeyPair(publicKeyFile, privateKeyFile, keyPair);
    }

    public static boolean checkSOKeys() {
        log.info("Checking keys");
        File publicKey = new File(PATH_PUBLIC_KEY);
        File privateKey = new File(PATH_PRIVATE_KEY);

        return publicKey.exists() && privateKey.exists();
    }

    public static String encrypt(String data) throws Exception {
        log.info("Encrypting data");
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        log.info("Data encrypted successfully");

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String data) throws Exception {
        log.info("Decrypting data");
        PrivateKey privateKey = getPrivateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(data));
        log.info("Data decrypted successfully");

        return new String(decryptedData);
    }

    public static PublicKey getPublicKey() throws IOException, ClassNotFoundException {
        log.info("Getting public key");
        ObjectInputStream publicKey = new ObjectInputStream(new FileInputStream(PATH_PUBLIC_KEY));
        return (PublicKey) publicKey.readObject();
    }

    public static PrivateKey getPrivateKey() throws IOException, ClassNotFoundException {
        log.info("Getting private key");
        ObjectInputStream privateKey = new ObjectInputStream(new FileInputStream(PATH_PRIVATE_KEY));
        return (PrivateKey) privateKey.readObject();
    }
}
