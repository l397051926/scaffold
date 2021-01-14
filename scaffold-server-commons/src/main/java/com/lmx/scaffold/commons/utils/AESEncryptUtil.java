package com.lmx.scaffold.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESEncryptUtil {

   private static final Logger log = LoggerFactory.getLogger(AESEncryptUtil.class);

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @param iv       使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 加密数据
     */
    public static String encrypt( String content,  String password,  String iv) {
        password="0000000000000000";
        iv = "0000000000000000";
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //密码key(超过16字节即128bit的key，需要替换jre中的local_policy.jar和US_export_policy.jar，否则报错：Illegal key size)
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(DEFAULT_ENCODING), KEY_ALGORITHM);
            //向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(DEFAULT_ENCODING));
            //初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            //加密
            final BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(cipher.doFinal(content.getBytes(DEFAULT_ENCODING)));
        } catch (Exception ex) {
            log.error("AES encrypt error, msg : " + ex.getMessage());
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content  密文
     * @param password 密码
     * @param iv       使用CBC模式，需要一个向量iv，可增加加密算法的强度
     * @return 明文
     */
    public static String decrypt( String content, String password,  String iv) {
        try {
            password="0000000000000000";
            iv = "0000000000000000";
            final BASE64Decoder decoder = new BASE64Decoder();
            byte[] byteContent = decoder.decodeBuffer(content);
            //创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //密码key
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(DEFAULT_ENCODING), KEY_ALGORITHM);
            //向量iv
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(DEFAULT_ENCODING));
            //初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
            //执行操作
            byte[] result = cipher.doFinal(byteContent);
            return new String(result, DEFAULT_ENCODING);
        } catch (Exception ex) {
            log.error("AES decrypt error, msg : " + ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String s = encrypt("asdffd", null, null);
        System.out.println(s);
        System.out.println(decrypt(s, null, null));
    }

}
