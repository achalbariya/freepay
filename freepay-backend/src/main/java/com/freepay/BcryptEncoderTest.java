package com.freepay;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder en = new BCryptPasswordEncoder();
		
		for(int i=1;i<=10;i++) {
			String enS = en.encode("bariya");
			System.out.println(enS);
		}

	}

}
