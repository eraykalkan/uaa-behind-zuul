package com.eray.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class UserController {


    private final AuthorizationServerTokenServices tokenServices;

    private final ConsumerTokenServices consumerTokenServices;

    @Autowired
    public UserController(AuthorizationServerTokenServices tokenServices,
                               ConsumerTokenServices consumerTokenServices
                               ) {
        this.tokenServices=tokenServices;
        this.consumerTokenServices=consumerTokenServices;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {

        System.out.println("User Data: " + user.getName());
        //System.out.println(tokenServices.getAccessToken(authentication));
        return user;
    }

    @DeleteMapping(value = "/revoketoken")
    public void revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            consumerTokenServices.revokeToken(tokenId);
        }
    }

}
