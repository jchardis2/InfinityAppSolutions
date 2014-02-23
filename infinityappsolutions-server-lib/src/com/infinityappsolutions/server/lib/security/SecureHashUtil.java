package com.infinityappsolutions.server.lib.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecureHashUtil {

	public static void main(String[] args) throws Exception {
		String message = "1";
		SecureHashUtil hash = new SecureHashUtil();
		String digest = hash.sha256Hash(message);
		System.out.println(digest);
	}

	public String sha256Hash(String message) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(message.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
