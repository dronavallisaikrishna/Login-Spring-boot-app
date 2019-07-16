package com.learnovercoffee.Login.controllers;


import com.learnovercoffee.Login.repositories.UserRepository;
import com.learnovercoffee.Login.dto.ApiResponse;
import com.learnovercoffee.Login.dto.AuthResponse;
import com.learnovercoffee.Login.dto.LoginRequest;
import com.learnovercoffee.Login.dto.LoginWithOTPRequest;
import com.learnovercoffee.Login.dto.LoginWithOTPValidatorRequest;
import com.learnovercoffee.Login.dto.SignUpRequest;
import com.learnovercoffee.Login.exceptionHandling.BadRequestException;
import com.learnovercoffee.Login.models.AuthProvider;
import com.learnovercoffee.Login.models.User;
import com.learnovercoffee.Login.security.TokenProvider;
import com.learnovercoffee.Login.services.impl.OTPGeneratorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private OTPGeneratorServiceImpl otpGenerationService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        
        System.out.println("From create user api");
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setMobileNumber(signUpRequest.getMobileNumber());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
    
    @PostMapping("/login-with-otp")
    public ResponseEntity<?> signInWithOTP(@Valid @RequestBody LoginWithOTPRequest loginWithOtpRequest){
    	System.out.println("login request is:-"+loginWithOtpRequest.toString());
    	User findOneByMobileNumber = userRepository.findOneByMobileNumber(loginWithOtpRequest.getMobileNumber());
    	if(findOneByMobileNumber==null) {
    		throw new BadRequestException("No user found with given mobile number");
    	}else {
    		String otp= otpGenerationService.generateRandomOTP(6);
    		boolean sendOTP = otpGenerationService.sendOTP(findOneByMobileNumber.getMobileNumber(),otp.toString());
    		if(sendOTP) {
    			findOneByMobileNumber.setOtp(otp.toString());
    			userRepository.save(findOneByMobileNumber);
    			return ResponseEntity.ok()
                        .body(new ApiResponse(sendOTP, "OTP sent successfully on mobile number"));
    		}else {
    			return ResponseEntity.ok()
                        .body(new ApiResponse(sendOTP, "OTP sending failed on mobile number"));
    		}
    	}
    }
    
    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateAndsignInWithOTP(@Valid @RequestBody LoginWithOTPValidatorRequest loginWithOtpValidatorRequest){
    	System.out.println("login request is:-"+loginWithOtpValidatorRequest.toString());
    	User findOneByMobileNumber = userRepository.findOneByMobileNumber(loginWithOtpValidatorRequest.getMobileNumber());
    	if(findOneByMobileNumber==null) {
    		throw new BadRequestException("No user found with given mobile number");
    	}else {
    		System.out.println(findOneByMobileNumber.getOtp().equals(loginWithOtpValidatorRequest.getOtp()));
    		if(findOneByMobileNumber.getOtp().equals(loginWithOtpValidatorRequest.getOtp())) {
    			findOneByMobileNumber.setOtp(null);
    			User updatedUser = userRepository.save(findOneByMobileNumber);
    			String token= tokenProvider.createTokenForUser(updatedUser);
    	        return ResponseEntity.ok(new AuthResponse(token));
    		}
    		else {
    			throw new BadRequestException("Invalid OTP.");
    		}
    	}
    }
}