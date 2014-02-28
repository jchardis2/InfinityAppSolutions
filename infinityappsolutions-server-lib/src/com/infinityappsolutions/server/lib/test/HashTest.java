package com.infinityappsolutions.server.lib.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTest {

	public static void main(String[] args) {
		String password = "leet4u?2";
		try {
			String digest = new String(MessageDigest.getInstance("SHA-256").digest(password.toString().getBytes()));
			System.out.println(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
