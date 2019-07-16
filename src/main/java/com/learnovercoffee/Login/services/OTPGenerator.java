package com.learnovercoffee.Login.services;

public interface OTPGenerator {
	
	public String generateRandomOTP(int numberOfDigits);
	
	public boolean sendOTP(String mobileNumber,String otp);
	
}
