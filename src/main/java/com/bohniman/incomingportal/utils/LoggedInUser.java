package com.bohniman.incomingportal.utils;

import com.bohniman.incomingportal.security.MyUserDetails;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * LoggedInUser
 */
public class LoggedInUser {

    public static String getLoggedInUsername() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;

    }

    public static MyUserDetails getLoggedInUser() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getLoggedInRole() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetails.getAuthorities().iterator().next().getAuthority();
    }

}