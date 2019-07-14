package com.learnovercoffee.Login.services.impl;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learnovercoffee.Login.configurations.AppProperties;
import com.learnovercoffee.Login.services.SMSProvider;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;

@Service
public class FastSmsProviderImplementation implements SMSProvider {
	
	Logger logger = LoggerFactory.getLogger(FastSmsProviderImplementation.class);
	
//	@Value("sms.apiKey")
//	private String apiKey;
//	
//	@Value("sms.senderId")
//	private String senderId;
	
	@Autowired
	private AppProperties appProperties;
	
	@Override
	public boolean sendSMS(String mobileNumber,String message) {
		try {
			String senderId= appProperties.getSms().getSenderId();
			String apiKey= appProperties.getSms().getApiKey();
			System.out.println("Sender id and api key are:-"+senderId+"  ... "+apiKey);
			String requestBody="sender_id="+senderId+"&message="+message+"&language=english&route=p&numbers="+mobileNumber;
			HttpResponse<String> smsResponse = Unirest.post("https://www.fast2sms.com/dev/bulk")
					  .header("authorization", apiKey)
					  .header("Content-Type", "application/x-www-form-urlencoded")
					  .body(requestBody)
					  .asString();
			System.out.println("Otp sending response is:-"+smsResponse.getStatus()+" body is:-"+smsResponse.getBody());
			return true;
		}catch (Exception e) {
			System.out.println("Exception occured while sending the message"+e);
			logger.error(e.toString());
			return false;
		}
	}
}
