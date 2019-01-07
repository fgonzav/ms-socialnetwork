package cl.social.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cl.social.configuration.SocialNetworkConfiguration;
import cl.social.configuration.SocialNetworkConfiguration.Configuration;
import cl.social.service.SocialNetworkService;
import lombok.extern.slf4j.Slf4j;

@Service("facebook")
@Slf4j
public class FacebookServiceImpl implements SocialNetworkService {

    private static final String FACEBOOK = "facebook";
    
    @Autowired
    private SocialNetworkConfiguration snConfig;
    
    @Override
    public String getAccessToken(String autorizationCode, String redirectUri) {
        log.info("[getAccessToken] autorizationCode: " + autorizationCode);
        log.info("[getAccessToken] redirectUri: " + redirectUri);
        
        RestTemplate rest = new RestTemplate();
        Configuration config = snConfig.getConfiguration(FACEBOOK);
        
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/v3.1/oauth/access_token")
                    .queryParam("client_id", "2192002814346297")
                    .queryParam("client_secret", "4f3c9ec07d51dbc50ec1e8ef4621cad3")
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("code", autorizationCode);
            
            log.info("[getAccessToken] endpoint: "+config.getAccessTokenEndpoint());
        
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(new HttpHeaders());
            
            return rest.exchange(builder.toUriString(), HttpMethod.GET, request, String.class).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error(exception instanceof HttpStatusCodeException? ((HttpStatusCodeException)exception).getResponseBodyAsString():exception.getMessage());
            throw exception;
        }
    }

    @Override
    public String getUserData(String accessToken) {
        log.info("[getUserData] accessToken: " + accessToken);
                
        RestTemplate rest = new RestTemplate();
        
        Configuration config = snConfig.getConfiguration(FACEBOOK);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://graph.facebook.com/v3.1/me")
                .queryParam("fields", "id,name,email,first_name,last_name")
                .queryParam("access_token", accessToken);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(new HttpHeaders());
     
        try {
            return rest.exchange(builder.toUriString(), HttpMethod.GET, request, String.class).getBody();
        } catch (HttpStatusCodeException exception) {
            log.error(exception instanceof HttpStatusCodeException? ((HttpStatusCodeException)exception).getResponseBodyAsString():exception.getMessage());
            throw exception;
        }
    }

}
