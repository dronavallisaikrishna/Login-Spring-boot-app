package com.learnovercoffee.Login.services;

public interface OTPGenerator {
	
	public int generateRandomOTP(int numberOfDigits);
	
	public boolean sendOTP(String mobileNumber,String otp);
	
}
