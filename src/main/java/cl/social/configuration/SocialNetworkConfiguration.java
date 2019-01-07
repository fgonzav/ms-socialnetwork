package cl.social.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties
@Getter
@Setter
@ToString
public class SocialNetworkConfiguration{

    private Configuration google;
    
    private Configuration facebook;
    
    public Configuration getConfiguration(String socialNetwork){
        switch(socialNetwork) {
            case "facebook":
                return facebook;
            case "google":
                return google;
            default:
                return null;
        }
    }
    
    @Getter
    @Setter
    @ToString
    public static class Configuration{
        private String accessTokenEndpoint;
        private String userProfileEndpoint;
        private String clientId;
        private String clientSecret;
        private String grantType;
        private String fields;
    }
}
