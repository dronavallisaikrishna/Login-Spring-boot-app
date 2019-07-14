package com.learnovercoffee.Login.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learnovercoffee.Login.configurations.AppProperties;
import com.learnovercoffee.Login.services.OTPGenerator;

import lombok.val;

@Service
public class OTPGeneratorServiceImpl implements OTPGenerator {


	@Autowired
	private AppProperties appProperties;
	
	@Autowired
	private FastSmsProviderImplementation fastSmsProvider;
	
	@Override
	public int generateRandomOTP(int n) {
		int m = (int) Math.pow(10, n - 1);
	    return m + new Random().nextInt(9 * m);
	}

	@Override
	public boolean sendOTP(String mobileNumber,String otp) {
		String smsTemplate = appProperties.getSms().getSmsTemplate();
		System.out.println("Message temlate is:-"+smsTemplate);
		String finalMessage = smsTemplate.replaceAll("ABC", otp);
		return fastSmsProvider.sendSMS(mobileNumber, finalMessage);
	}
}
