package com.springboot.swagger.demo.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.springboot.swagger.demo.domain.ApplicationUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import static com.springboot.swagger.demo.security.SecurityConstants.EXPIRATION_TIME;
import static com.springboot.swagger.demo.security.SecurityConstants.HEADER_STRING;
import static com.springboot.swagger.demo.security.SecurityConstants.TOKEN_PREFIX;
import static com.springboot.swagger.demo.security.SecurityConstants.SECRET;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    /**
     * Parse users credentials and issue them to authentication manager.
     */
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            ApplicationUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        }
        catch(MismatchedInputException e){
            throw new BadCredentialsException("Invalid request.Valid request is {\"username\":\"username\",\"password\":\"passwpord\"}");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //Generate AWT here on successful authentication.
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        if(failed instanceof  BadCredentialsException){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        else if(failed instanceof UsernameNotFoundException){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        else
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        response.getWriter().write(failed.getMessage());
        response.flushBuffer();

    }
}
