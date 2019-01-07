package cl.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.social.configuration.SocialNetworkServiceFactory;
import cl.social.model.SocialNetworkAuthRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path="/social")
@Slf4j
public class LoginSocialNetworkController {
    
    @Autowired
    private SocialNetworkServiceFactory factory;

    @PostMapping(path="/{social-network}/auth")
    public ResponseEntity<String> login(@PathVariable(name="social-network") String socialNetwork, @RequestBody SocialNetworkAuthRequest request) {
        log.info("[login] socialNetwork: " + socialNetwork);
        log.info("[login] request: " + request);
        String response = factory.getService(socialNetwork).getAccessToken(request.getAutorizationCode(), request.getRedirectUri());
        log.info("[login] response: " + response);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping(path="/{social-network}/user")
    public ResponseEntity<String> getUserData(@PathVariable(name="social-network") String socialNetwork, @RequestParam(name="accessToken") String accessToken){
        return new ResponseEntity<>(factory.getService(socialNetwork).getUserData(accessToken), HttpStatus.OK);
    }
}
