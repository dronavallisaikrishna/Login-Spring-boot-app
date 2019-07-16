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
	public String generateRandomOTP(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "abcdefghijklmnopqrstuvxyz";  
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) {  
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random());  
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        }
  
        return sb.toString();
	}

	@Override
	public boolean sendOTP(String mobileNumber,String otp) {
		String smsTemplate = appProperties.getSms().getSmsTemplate();
		System.out.println("Message temlate is:-"+smsTemplate);
		String finalMessage = smsTemplate.replaceAll("ABC", otp);
		return fastSmsProvider.sendSMS(mobileNumber, finalMessage);
	}
}
