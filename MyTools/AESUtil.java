package com.app.scmaTest;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
/**通常mysql中AES加密，和java中不一致，
 * 也就是说mysql中AES加密的东西，copy出来，
 * 用java代码不能解密
 * 下面大佬就来秀一波操作
 * */
public class AESUtil {
	/**加密的Key*/
	private static String  AESKEY = "zhfw_700";
	/**同意是用的编码*/
	private static String AESCODE  = "UTF-8";
	
	
	public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
        try {
            final byte[] finalKey = new byte[16];
            int i = 0;
            for(byte b : key.getBytes(encoding))
                finalKey[i++%16] ^= b;          
            return new SecretKeySpec(finalKey, "AES");
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
  
	/**AES 解密
	 * data : 待解密的数据 
	 * */
	public static String decrpt(String data)  throws Exception {
		 // Decrypt
        final Cipher decryptCipher = Cipher.getInstance("AES");                         
        decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey(AESKEY, AESCODE));
        return new String(decryptCipher.doFinal(Hex.decodeHex(data.toCharArray())));
	}
	
	/**AES加密
	 * data : 待加密 的数据 
	 * */
	public static String encrpt(String data)  throws Exception {
		 // Encrypt
        final Cipher encryptCipher = Cipher.getInstance("AES");                         
        encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey(AESKEY, AESCODE));       
        char[] code=  Hex.encodeHex(encryptCipher.doFinal(data.getBytes(AESCODE)));
        StringBuilder builder = new StringBuilder();
        for(char d:code) {
        	builder.append(d);
        }
        String strning = builder.toString();
        return strning;

	}
	
	
 public static void main(String... args) throws Exception {
    	/*
    	 * 
    	 * 加密数据 查询的时候 使用MySQl 自带的解密
INSERT INTO  kettle_link_database_info(id,school_db_host) VALUES('1111254',HEX(AES_ENCRYPT(('针对varchar类型加密'),'coco') )); 
SELECT AES_DECRYPT(UNHEX(school_db_host), 'dp700') FROM kettle_link_database_info;


INSERT INTO  kettle_link_database_info(id,school_db_host) VALUES('111254',AES_ENCRYPT(('针对二进制类型加密'),'coco')); 
SELECT AES_DECRYPT(school_db_host, 'dp700') FROM kettle_link_database_info;

    	 * 
    	 * */
        // Decrypt
//        final Cipher decryptCipher = Cipher.getInstance("AES");                         
//        decryptCipher.init(Cipher.DECRYPT_MODE, generateMySQLAESKey("coco", "UTF-8"));
//        System.out.println(new String(decryptCipher.doFinal(Hex.decodeHex("d1314e21a741b85e00fb975529a217ac98fab03b6fca017c0a6bf1cd621ef7ae".toCharArray()))));
  
         
        // Encrypt
//        final Cipher encryptCipher = Cipher.getInstance("AES");                         
//        encryptCipher.init(Cipher.ENCRYPT_MODE, generateMySQLAESKey("coco", "UTF-8"));    
//        System.out.println(Hex.encodeHex(encryptCipher.doFinal("hsot:/10.31.46.109".getBytes("UTF-8"))));
        
        System.out.println(encrpt("hsot:/10.31.46.109gtrhggsregresgthtrdhtrehrthyt.fjytyfjytjgfhrt")); //加密 
        System.out.println(decrpt(encrpt("hsot:/10.31.46.109"))); //解密 加密的数据
        
    }
}