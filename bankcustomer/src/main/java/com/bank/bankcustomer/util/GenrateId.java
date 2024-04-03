package com.bank.bankcustomer.util;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenrateId {
	
	public BigInteger genrateCustomerId() {
		String yearMonth = new SimpleDateFormat("YYYYMM").format(new Date());
		System.out.println(yearMonth);
		String digits = "0123456789";
		StringBuilder randomID = new StringBuilder();
		randomID.append(yearMonth.substring(2, 6));
		Random random = new Random();
		for(int i = 0;i < 8;i++) {
			randomID.append(digits.charAt(random.nextInt(digits.length())));
		}
		return null;
	}
}
