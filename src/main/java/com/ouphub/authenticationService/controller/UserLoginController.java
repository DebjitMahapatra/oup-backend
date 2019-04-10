package com.ouphub.authenticationService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouphub.authenticationService.model.JwtAuthenticationToken;
import com.ouphub.authenticationService.model.JwtUser;
import com.ouphub.authenticationService.model.UserAuthentication;
import com.ouphub.authenticationService.security.JwtGenerator;

@CrossOrigin("*")
@RestController
@RequestMapping
public class UserLoginController {
	
	private JwtGenerator jwtGenerator;

    public UserLoginController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    
    @PostMapping(value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JwtAuthenticationToken UserAuthentication(@RequestBody final UserAuthentication userAuthentication) throws JsonProcessingException {
    	
    	JwtUser jwtUser=new JwtUser();
    	jwtUser.setId(123);
    	jwtUser.setRole(userAuthentication.getPassword());
    	jwtUser.setUserName(userAuthentication.getUsername());
    	JwtAuthenticationToken jwtToken=new JwtAuthenticationToken(jwtGenerator.generate(jwtUser));
    	System.out.println("Success" + jwtToken.toString());
    	return jwtToken;
   }
    
}
