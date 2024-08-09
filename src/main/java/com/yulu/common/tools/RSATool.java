package com.yulu.common.tools;

import com.yulu.common.enums.Constants;
import com.yulu.common.exceptions.ServiceException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 加解密工具类
 */
public class RSATool {

    // 生成公钥私钥
    public static Map createPubPri() {
        try {
            KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥对生成器，密钥大小为96-1024位
            rsa.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = rsa.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 私钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 公钥
            String pubkey = new String(Base64.encodeBase64(publicKey.getEncoded()));
            // 得到私钥字符串
            String prikey = new String(Base64.encodeBase64((privateKey.getEncoded())));

            Map<String, String> map = new HashMap<>();
            map.put("pubkey", pubkey);
            map.put("prikey", prikey);
            return map;
        } catch (Exception e) {
            throw new ServiceException();
        }
    }

    /**
     * 加密
     * @param str 需要加密的数据
     * @param pubkey 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String pubkey) throws Exception {
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(pubkey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    }

    /**
     * 解密
     * @param str 需要解密的数据
     * @param prikey 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String prikey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        // base64编码的私钥
        byte[] decoded = Base64.decodeBase64(prikey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}
