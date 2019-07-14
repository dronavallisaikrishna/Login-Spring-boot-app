package com.learnovercoffee.Login.dto;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginWithOTPValidatorRequest {
	
	@NonNull
	@Size(min=10,max=12)
	private String mobileNumber;
	
	@NonNull
	private String otp;
}
