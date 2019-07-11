package com.learnovercoffee.Login.security.oauth2.users;

import java.util.Map;

import com.learnovercoffee.Login.exceptionHandling.OAuth2AuthenticationProcessingException;
import com.learnovercoffee.Login.models.AuthProvider;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
    	System.out.println("user attributes are:-"+attributes);
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}