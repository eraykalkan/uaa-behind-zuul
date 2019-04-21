package com.eray.leadservice.controller;


import com.eray.leadservice.dto.TestDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping(value = "/test")
    public TestDTO test(Principal principal, OAuth2Authentication oAuth2Authentication) {

        TestDTO testDTO=new TestDTO();
        testDTO.setAd("eray");
        testDTO.setSoyad("k");
        testDTO.setEmail("ananas@ananas.com");

        System.out.println(principal.getName());

        System.out.println(oAuth2Authentication);

        return testDTO;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/yetki")
    public void yetki(Principal principal, OAuth2Authentication oAuth2Authentication) {

        System.out.println(principal.getName());

        System.out.println(oAuth2Authentication);

    }
}
