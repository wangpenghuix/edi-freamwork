package com.avatech.edi.common.tools;


import com.avatech.edi.common.exception.BusinessException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
/**
 * @author Fancy
 * @date 2019/8/3
 */
public class Encrypt {
    private String strDefaultKey = "ysyj-20190710123";
    private byte[] key = strDefaultKey.getBytes();

    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HMACSHA1 = "HmacSHA1";

    private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; //RFC2401
    private static final int DEFAULT_AES_KEYSIZE = 128;
    private static final int DEFAULT_IVSIZE = 16;

    private static SecureRandom random = new SecureRandom();

    public Encrypt(){
    }
    public Encrypt(String strDefaultKey){
        this.strDefaultKey = strDefaultKey;
        this.key = strDefaultKey.getBytes();
    }

    //-- HMAC-SHA1 funciton --//
    /**
     * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节.
     *
     * @param input 原始输入字符数组
     * @param key HMAC-SHA1密钥
     */
    public static byte[] hmacSha1(byte[] input, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
            Mac mac = Mac.getInstance(HMACSHA1);
            mac.init(secretKey);
            return mac.doFinal(input);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BusinessException("1101,使用HMAC-SHA1进行消息签名出错：",e);
        }
    }

    /**
     * 校验HMAC-SHA1签名是否正确.
     *
     * @param expected 已存在的签名
     * @param input 原始输入字符串
     * @param key 密钥
     */
    public static boolean isMacValid(byte[] expected, byte[] input, byte[] key) {
        byte[] actual = hmacSha1(input, key);
        return Arrays.equals(expected, actual);
    }

    /**
     * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节).
     * HMAC-SHA1算法对密钥无特殊要求, RFC2401建议最少长度为160位(20字节).
     */
    public static byte[] generateHmacSha1Key() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
            keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BusinessException("1101,生成HMAC-SHA1密钥出错：",e);
        }
    }

    /**
     * 使用AES加密原始字符串.
     *
     * @param input 原始输入字符数组
     */
    public byte[] aesEncrypt(byte[] input) throws Exception {
        if (key.length != 16) {
            throw new Exception("Key长度不是16位");
        }

        return aes(input, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES加密原始字符串.
     *
     * @param input 原始输入字符串
     */
    public String aesEncrypt(String input) {
        try{
            byte[] encryptResult = aesEncrypt(input.getBytes("utf-8"));
            return encodeHex(encryptResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("1101,生成HMAC-SHA1密钥出错：",e);
        }
    }

    /**
     * 使用AES加密原始字符串
     * @param input 原始输入字符数组
     * @param key 符合AES要求的密钥
     * @param iv 初始向量
     */
    public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) {
        return aes(input, key, iv, Cipher.ENCRYPT_MODE);
    }

    /**
     * 使用AES解密字符串, 返回原始字符串.
     *
     * @param input Hex编码的加密字符串
     * @throws UnsupportedEncodingException
     */
    public String aesDecrypt(byte[] input) throws Exception {
        if (key.length != 16) {
            throw new Exception("Key长度不是16位");
        }
        byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
        //return new String(decryptResult);
        return new String(decryptResult,"utf-8");
    }

    /**
     * 使用AES解密字符串, 返回原始字符串.
     *
     * @param input Hex编码的加密字符串
     * @throws UnsupportedEncodingException
     */
    public String aesDecrypt(String input) {
        try{
            return aesDecrypt(decodeHex(input));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("1101,解密出错：",e);
        }
    }

    /**
     * 使用AES解密字符串, 返回原始字符串.
     * @param input Hex编码的加密字符串
     * @param key 符合AES要求的密钥
     * @param iv 初始向量
     * @throws UnsupportedEncodingException
     */
    public static String aesDecrypt(byte[] input, byte[] key, byte[] iv) throws UnsupportedEncodingException {
        byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
        return new String(decryptResult,"utf-8");
    }

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key 符合AES要求的密钥
     * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, secretKey);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BusinessException("1101,使用AES加密或解密无编码的原始字节数组出错：",e);
        }
    }

    /**
     * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
     *
     * @param input 原始字节数组
     * @param key 符合AES要求的密钥
     * @param iv 初始向量
     * @param mode Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
     */
    private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BusinessException("1101,使用AES加密或解密无编码的原始字节数组：",e);
        }
    }

    /**
     * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
     */
    public static byte[] generateAesKey() {
        return generateAesKey(DEFAULT_AES_KEYSIZE);
    }

    /**
     * 生成AES密钥,可选长度为128,192,256位.
     */
    public static byte[] generateAesKey(int keysize) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(keysize);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey.getEncoded();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BusinessException("1102,生成AES密钥出错：",e);
        }
    }


    /**
     * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
     */
    public static byte[] generateIV() {
        byte[] bytes = new byte[DEFAULT_IVSIZE];
        random.nextBytes(bytes);
        return bytes;
    }

    public byte[] getKey() {
        return key;
    }
    public void setKey(byte[] key) {
        this.key = key;
    }


    /**
     * Hex编码.
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new BusinessException("1103,Hex解码出错：", e);
        }
    }
}
