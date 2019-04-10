package com.ouphub.authenticationService.controller;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ouphub.authenticationService.model.JwtAuthenticationToken;
import com.ouphub.authenticationService.model.JwtUser;
import com.ouphub.authenticationService.model.User;
import com.ouphub.authenticationService.model.UserAuthentication;
import com.ouphub.authenticationService.security.JwtGenerator;

@CrossOrigin("*")
@RestController
@RequestMapping
public class UserRegistrationController {
	
	private JwtGenerator jwtGenerator;

    public UserRegistrationController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    
    @PostMapping(value="/registration", produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JwtAuthenticationToken RegisterUser(@RequestBody final User user) throws JsonProcessingException {
    	
    	JwtUser jwtUser=new JwtUser();
    	jwtUser.setId(123);
    	jwtUser.setRole(user.getPassword());
    	jwtUser.setUserName(user.getFirstName());
    	JwtAuthenticationToken jwtToken=new JwtAuthenticationToken(jwtGenerator.generate(jwtUser));
    	System.out.println("Success" + jwtToken.toString());
    	return jwtToken;
   }

}
