package com.example.mykotlin;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private Cipher cipher;

    public static AESUtil getEncrypt(byte[] key, byte[] iv)
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException
            , InvalidKeyException, NoSuchPaddingException {
        return new AESUtil(Cipher.ENCRYPT_MODE, key, iv);
    }

    public static AESUtil getDecrypt(byte[] key, byte[] iv)
            throws InvalidAlgorithmParameterException, NoSuchAlgorithmException
            , InvalidKeyException, NoSuchPaddingException {
        return new AESUtil(Cipher.DECRYPT_MODE, key, iv);
    }

    private AESUtil(int mode, byte[] key, byte[] iv)
            throws NoSuchPaddingException, NoSuchAlgorithmException
            , InvalidAlgorithmParameterException, InvalidKeyException {
        cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(mode, new SecretKeySpec(key, KEY_ALGORITHM), new IvParameterSpec(iv));
    }

    public byte[] update(byte[] data) {
        return cipher.update(data);
    }

    public byte[] finish(byte[] data) throws BadPaddingException, IllegalBlockSizeException {
        return cipher.doFinal(data);
    }


    /**
     * Author: QinHao
     * Email:qinhao@jeejio.com
     * Date:2021/6/11 16:08
     * Description:Decrypt file,write file by dst.
     */
    public static void decrypt(byte[] key, byte[] iv, String src, String dst) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
        AESUtil aesUtil = AESUtil.getDecrypt(key, iv);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(dst);
            boolean isFinal = false;
            while (!isFinal) {
                byte[] buf = new byte[1024 * 16];
                if (inputStream.available() <= buf.length) {
                    buf = new byte[inputStream.available()];
                    isFinal = true;
                }
                inputStream.read(buf);
                byte[] desc = isFinal ? aesUtil.finish(buf) : aesUtil.update(buf);
                outputStream.write(desc);
                outputStream.flush();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Author: QinHao
     * Email:qinhao@jeejio.com
     * Date:2021/6/11 16:08
     * Description:Decrypt file,return byte array.
     */
    public static byte[] decrypt(byte[] key, byte[] iv, byte[] src) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            AESUtil aesUtil = AESUtil.getDecrypt(key, iv);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            int readLen = 1024 * 16;
            while (len < src.length) {
                byte[] data;
                if (len + readLen > src.length) {
                    // Read finish.
                    data = Arrays.copyOfRange(src, len, len = src.length);
                    data = aesUtil.finish(data);
                } else {
                    data = Arrays.copyOfRange(src, len, len = len + readLen);
                    data = aesUtil.update(data);
                }
                byteArrayOutputStream.write(data);
                byteArrayOutputStream.flush();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
