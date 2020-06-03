package com.bohniman.incomingportal.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import nl.captcha.Captcha;

@Component
public class CaptchaAuthenticationProvider extends DaoAuthenticationProvider {

    private static Logger log = LoggerFactory.getLogger(CaptchaAuthenticationProvider.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
        super.setPasswordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        super.additionalAuthenticationChecks(userDetails, authentication);

        Object object = authentication.getDetails();
        if (!(object instanceof CaptchaDetails)) {
            throw new InsufficientAuthenticationException("Captcha Details Not Found.");
        }

        CaptchaDetails captchaDetails = (CaptchaDetails) object;
        Captcha captcha = captchaDetails.getCaptcha();

        if (captcha != null) {
            if (!captcha.getAnswer().equals(captchaDetails.getAnswer())) {
                throw new InsufficientAuthenticationException("Invalid Captcha");
            }
        }
    }

}
