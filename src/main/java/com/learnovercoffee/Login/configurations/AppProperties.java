package com.learnovercoffee.Login.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();
    private final Sms sms = new Sms();

    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    public static final class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
    
    public static final class Sms {
    	private String apiKey;
    	private String senderId;
    	private String smsTemplate;
		
    	public String getApiKey() {
			return apiKey;
		}
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		public String getSenderId() {
			return senderId;
		}
		public void setSenderId(String senderId) {
			this.senderId = senderId;
		}
		public String getSmsTemplate() {
			return smsTemplate;
		}
		public void setSmsTemplate(String smsTemplate) {
			this.smsTemplate = smsTemplate;
		}
    }

    
    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getOauth2() {
        return oauth2;
    }
    
    public Sms getSms() {
    	return sms;
    }
}
