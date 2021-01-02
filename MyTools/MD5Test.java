package com.app.scmaTest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;


public class MD5Test {
	
	//生成普通的MD5码
	public static String MD5(String input) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "check jdk";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = input.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	//生成“盐”和加盐后的MD5码，并将盐混入到MD5码中
	public static String generate(String password) {
	    //生成一个16位的随机数，也就是所谓的“盐”
		Random r = new Random();
 		StringBuilder sb = new StringBuilder(16);
 		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
 		int len = sb.length();
 		if (len < 16) {
 			for (int i = 0; i < 16 - len; i++) {
 				sb.append("0");
 			}
 		}
 		String salt = sb.toString();
 		//将“盐”加到明文中，并生成新的MD5码
 		password = md5Hex(password + salt);
 		//将“盐”混到新生成的MD5码中，之所以这样做是为了后期更方便的校验明文和秘文，也可以不用这么做，不过要将“盐”单独存下来，不推荐这种方式
 		char[] cs = new char[48];
 		for (int i = 0; i < 48; i += 3) {
 			cs[i] = password.charAt(i / 3 * 2);
 			char c = salt.charAt(i / 3);
 			cs[i + 1] = c;
 			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
 		}
		return new String(cs);
	}
	
	//验证明文和加盐后的MD5码是否匹配
	public static boolean verify(String password, String md5) {
		//先从MD5码中取出之前加的“盐”和加“盐”后生成的MD5码
 		char[] cs1 = new char[32];
		char[] cs2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			cs1[i / 3 * 2] = md5.charAt(i);
			cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			cs2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(cs2);
		//比较二者是否相同
		return md5Hex(password + salt).equals(new String(cs1));
	}

	//生成MD5
	private static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String args[]) {
		
		String plaintext = "junfan";

		System.out.println("明文：" + plaintext);
		System.out.println("普通MD5码：" + MD5(plaintext));
 
		// 获取加盐后的MD5值
		String ciphertext = generate(plaintext);
		System.out.println("加盐后MD5码：" + ciphertext);
		System.out.println("是否是同一字符串:" + verify(plaintext, ciphertext));
		
	}

}

