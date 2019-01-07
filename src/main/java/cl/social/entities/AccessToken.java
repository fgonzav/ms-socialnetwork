package cl.social.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccessToken {

    private String access_token;
    
    private Integer expires_in;
    
    private String token_type;
}
