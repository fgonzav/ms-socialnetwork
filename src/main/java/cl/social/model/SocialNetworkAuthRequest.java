package cl.social.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SocialNetworkAuthRequest {
    
    private String autorizationCode;
    
    private String redirectUri;
}
