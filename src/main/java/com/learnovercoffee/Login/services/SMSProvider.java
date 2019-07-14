package com.learnovercoffee.Login.services;

public interface SMSProvider {
	
	public boolean sendSMS(String mobileNumber,String message);
}
