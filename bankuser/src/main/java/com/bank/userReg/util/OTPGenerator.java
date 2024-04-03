package com.bank.userReg.util;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component	
public class OTPGenerator {
	public static String genrateOtp() {
		Supplier<String> otp = ()->{
			String otpGen = "";
			for(int i = 0;i < 6;i++) {
				otpGen = otpGen+(int)(Math.random()*10);
			}
			return otpGen;
		};
		return otp.get();
	}
}
