package com.merrifield.Essentialism.API.controllers;

import com.merrifield.Essentialism.API.models.UserModels.User;
import com.merrifield.Essentialism.API.models.UserModels.UserMinimum;
import com.merrifield.Essentialism.API.models.JoinTableModels.UserRole;
import com.merrifield.Essentialism.API.services.RoleService;
import com.merrifield.Essentialism.API.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
public class AuthenticationAndRegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    TokenStore tokenStore;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registerUser(
            HttpServletRequest httpServletRequest,
            @RequestBody @Valid UserMinimum newUser)
    {
        User user = new User();

        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        user.setRoles(Set.of(new UserRole(user, roleService.findByName("USER"))));

        long id = userService.addUser(user);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/users/{userId}")
                .buildAndExpand(id)
                .toUri();
        responseHeaders.setLocation(newUserURI);

        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"), System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
                "password");
        map.add("scope",
                "read write trust");
        map.add("username",
                newUser.getUsername());
        map.add("password",
                newUser.getPassword());

        HttpEntity request = new HttpEntity(map, headers);

        String token = restTemplate.postForObject(requestURI, request, String.class);

        return new ResponseEntity<>(token, responseHeaders, HttpStatus.CREATED);

    }

    @GetMapping(value = {"/oauth/revoke-token, logout"}, produces = "application/json")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest){
        String authHeader = httpServletRequest.getHeader("Authorization");

        if(authHeader != null){
            String token = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);

            tokenStore.removeAccessToken(accessToken);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
