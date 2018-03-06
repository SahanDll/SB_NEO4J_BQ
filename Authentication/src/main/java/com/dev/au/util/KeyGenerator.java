package com.dev.au.util;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by N5608296 on 12/09/17.
 */
public class KeyGenerator {
    private static final Logger logger = LoggerFactory.getLogger(KeyGenerator.class);
    private static KeyGenerator self;
    private static final int PASSWORD_STRENGTH = 256;
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 512;
    private static byte[] publicKey;
    private static byte[] privateKey;

    private KeyGenerator(){
        /*Implementation not required*/
    }

    public static synchronized KeyGenerator getInstance() {
        if (self == null) {
            self = new KeyGenerator();
            try {
                KeyPair kp = generateKeyPair();
                Key pub = kp.getPublic();
                Key pvt = kp.getPrivate();
                publicKey = pub.getEncoded();
                privateKey = pvt.getEncoded();
            } catch (Exception e) {
                logger.error("Error : ", e);
            }
        }
        return self;
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(KEY_SIZE, random);

        return keyGen.generateKeyPair();
    }

    public String encodePassword(String password){
        ShaPasswordEncoder s = new ShaPasswordEncoder(PASSWORD_STRENGTH);
        return s.encodePassword(password,"");
    }

    public byte[] encrypt(byte[] inputData) throws Exception {
        PublicKey key = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKey));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PUBLIC_KEY, key);

        return cipher.doFinal(inputData);
    }

    public byte[] decrypt(byte[] inputData) throws Exception {
        PrivateKey key = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKey));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PRIVATE_KEY, key);

        return cipher.doFinal(inputData);
    }

    public static String encryptWithExternalKey(byte[] publicKey, byte[] inputData)
            throws Exception {

        PublicKey key = KeyFactory.getInstance(ALGORITHM)
                .generatePublic(new X509EncodedKeySpec(publicKey));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PUBLIC_KEY, key);

        byte[] encryptedBytes = cipher.doFinal(inputData);

        return java.util.Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static byte[] decryptWithExternalKey(byte[] privateKey, byte[] inputData)
            throws Exception {

        PrivateKey key = KeyFactory.getInstance(ALGORITHM)
                .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.PRIVATE_KEY, key);

        byte[] decryptedBytes = cipher.doFinal(inputData);

        return decryptedBytes;
    }

    public String getPublicKey() {

        return java.util.Base64.getEncoder().encodeToString(publicKey);
    }
}
