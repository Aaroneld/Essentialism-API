package com.merrifield.Essentialism.API.services;

;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

public final class AuthorizationUtilities {


    public static boolean affectedResourceIsNotOwnedByLoggedInUser(Authentication a, String username){
        org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) a.getPrincipal();

        return !loggedInUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).contains("ADMIN")
                && !loggedInUser.getUsername().equals(username);
    }
}
