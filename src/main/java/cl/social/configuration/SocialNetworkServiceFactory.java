package cl.social.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cl.social.service.SocialNetworkService;

@Component
public class SocialNetworkServiceFactory {

    @Autowired
    @Qualifier("google")
    private SocialNetworkService googleService;
    
    @Autowired
    @Qualifier("facebook")
    private SocialNetworkService facebookService;
    
    public SocialNetworkService getService(String socialNetwork) {
        switch(socialNetwork) {
            case "facebook":
                return facebookService;
            case "google":
                return googleService;
            default:
                return null;
        }
    }
}
