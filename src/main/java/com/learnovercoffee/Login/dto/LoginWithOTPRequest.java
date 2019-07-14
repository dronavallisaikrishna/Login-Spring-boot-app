package com.learnovercoffee.Login.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginWithOTPRequest{
	
	@NonNull
	@Size(min=10,max=12)
	private String mobileNumber;
}
