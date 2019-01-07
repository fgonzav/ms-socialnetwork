package cl.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cl.social.annotation.Loggable;
import cl.social.configuration.SocialNetworkConfiguration;
import cl.social.configuration.SocialNetworkConfiguration.Configuration;
import cl.social.entities.AccessToken;
import cl.social.service.SocialNetworkService;
import cl.social.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Service("google")
@Slf4j
public class GoogleServiceImpl implements SocialNetworkService{

    private static final String GOOGLE = "google";
    
    @Autowired
    private SocialNetworkConfiguration snConfig;
    
    @Override
    @Loggable
    public String getAccessToken(String autorizationCode, String redirectUri) {
        RestTemplate rest = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Configuration config = snConfig.getConfiguration(GOOGLE);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        
        log.info("[getAccessToken] config: " + config);
        map.add("code", autorizationCode);
        map.add("client_id", "566722109821-accbnvslqf8st1si4hocn3j9sfkvv8n0.apps.googleusercontent.com");
        map.add("client_secret", "DuhL91SZ-gj9xxzeYtNjgCuh");
        map.add("redirect_uri", redirectUri);
        map.add("grant_type", "authorization_code");
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        log.info("[getAccessToken] request: " + request);
        
        return JsonUtil.toJson(rest.postForEntity("https://www.googleapis.com/oauth2/v4/token", request, AccessToken.class).getBody());
    }
    
    @Override
    public String getUserData(String accessToken) {
        log.info("[getUserData] accessToken: " + accessToken);
        RestTemplate rest = new RestTemplate();
        
        Configuration config = snConfig.getConfiguration(GOOGLE);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s",accessToken));
        
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("fields", "email,name,family_name,given_name,id,verified_email");
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        return rest.exchange("https://www.googleapis.com/oauth2/v2/userinfo", HttpMethod.GET, request, String.class).getBody();
    }
}
