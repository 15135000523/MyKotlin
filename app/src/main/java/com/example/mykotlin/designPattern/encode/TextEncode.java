package com.example.mykotlin.designPattern.encode;

import android.util.Log;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class TextEncode {

    private static TextEncode instance;

    private TextEncode() {
    }

    public static TextEncode getInstance() {
        if (instance == null) {
            synchronized (TextEncode.class) {
                if (instance == null) {
                    instance = new TextEncode();
                }
            }
        }
        return instance;
    }

    public void textDESEncode(String str) {
        try {
            //返回生成指定算法密钥的KeyGenerator对象
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            //初始化此密钥生成器,使其具有确定的密钥大小
            keyGenerator.init(56);
            //生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteSecretKey = secretKey.getEncoded();

            // key转换
            DESKeySpec desKeySpec = new DESKeySpec(byteSecretKey); //实例化DES密钥规则
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES"); //实例化密钥工厂
            Key convertSecretKey = factory.generateSecret(desKeySpec); //生成密钥

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());
            System.out.println("加密前:" + str);
            System.out.println("加密后:" + new String(result));

            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("解密后:" + new String(result));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

    }

    public void textAES(String str) {

        try {
            //key zhai呢瑞特
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesSecreskey = secretKey.getEncoded();

            //将获取的密钥数组转换成对应的密钥
            Key key = new SecretKeySpec(bytesSecreskey, "AES");
//            System.out.println(key.getFormat());
            //加密
            /**
             * AES/ECB/PKCS5Padding
             * AES 表示加解密类型: AES,AESWrap,ARCFOUR,Blowfish,DES,DESede,DESedeWrap,ECIES,RC2,RC4,RC5,RSA
             * ECB 工作方式 (NODE,CBC,CCM,CTR,CTS,ECB,GCM,PCBC)
             * PKCS5Padding 填充方式(NoPadding,ISO10126Padding,PKCS5Padding,PKCS1Padding,SSL3Padding)
             */
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] res = cipher.doFinal(str.getBytes());

            System.out.println("加密前：" + str);
            System.out.println("加密后：" + new String(res));

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            res = cipher.doFinal(res);
            System.out.println("解密后：" + new String(res));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }


    }

    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";


    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;
    //公钥
    public static final String PUBLIC_KEY = "RSAPublicKey";

    //私钥
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    private static HashMap<String, Key> keyMap = new HashMap<>();

    /**
     * 非对称加密
     * 使用公钥对数据进行加密得到密文
     * 使用私钥对密文进行解密得到数据
     * 用对方的公钥加密，对方用自己的私钥解密
     * <p>
     * 数字签名
     * 使用自己的私钥进行签名，
     * 对方使用公钥进行验证.
     * RSA 加密解密 数字签名
     * DSA 数字签名(数字签名速度快，)
     */
    public void textRSA() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();//生成RSA密钥对
            keyMap.put(PUBLIC_KEY, keyPair.getPublic());
            keyMap.put(PRIVATE_KEY, keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取密钥
     *
     * @param type
     * @return
     */
    public byte[] getKeybyte(String type) {
        return keyMap.get(type).getEncoded();
    }

    /**
     * 获取密钥
     *
     * @param type
     * @return
     */
    public Key getKey(String type) {
        return keyMap.get(type);
    }

    /**
     * 私钥签名
     *
     * @param data
     * @param key
     * @return
     */
    public byte[] encryptByPrivateKey(byte[] data, byte[] key) {
        byte[] encodeData = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            encodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encodeData;
    }

    /**
     * 公钥加密
     *
     * @param data
     * @param key
     * @return
     */
    public byte[] encryptByPublicKey(byte[] data, byte[] key) {
        byte[] encodeData = null;
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(spec);
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encodeData;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @param key
     * @return
     */
    public byte[] decryptByPrivateKey(byte[] data, byte[] key) {
        byte[] decodeData = null;
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = factory.generatePrivate(spec);
            Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decodeData;
    }

    /**
     * 公钥解密
     *
     * @param data
     * @param key
     * @return
     */
    public byte[] decryptByPublicKey(byte[] data, byte[] key) {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        byte[] decodeData = null;
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(spec);
            Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            decodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decodeData;
    }

    /**
     * 公钥加密
     *
     * @param data 数据
     * @return 加密后的数据
     */
    public byte[] encode(byte[] data) {
        byte[] encodeData = null;
        try {
            Cipher cipher = Cipher.getInstance(keyMap.get(PUBLIC_KEY).getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, keyMap.get(PUBLIC_KEY));
            encodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encodeData;
    }

    /**
     * 私钥解密
     *
     * @param data
     * @return
     */
    public byte[] decode(byte[] data) {
        byte[] encodeData = null;
        try {
            Cipher cipher = Cipher.getInstance(keyMap.get(PRIVATE_KEY).getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, keyMap.get(PRIVATE_KEY));
            encodeData = cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encodeData;
    }

    /**
     * 专门用来做签名验证的
     */
    public void textDSA() {

    }

}
