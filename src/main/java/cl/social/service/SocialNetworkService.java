package cl.social.service;

public interface SocialNetworkService {

    public String getAccessToken(String autorizationCode, String redirectUri);
    
    public String getUserData(String accessToken);
    
}
