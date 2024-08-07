package com.yulu.common.tools;

import com.yulu.common.exceptions.ServiceException;
import com.yulu.entity.system.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 加解密工具类
 */
public class RSATool {

    // 生成公钥私钥
    public Keys createPubPri() {
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
            Keys keys = new Keys();
            keys.setPrikey(prikey);
            keys.setPubkey(pubkey);
            keys.setCreateTime(DateTool.getDate());
            return keys;
        } catch (Exception e) {
            throw new ServiceException();
        }
    }
}
