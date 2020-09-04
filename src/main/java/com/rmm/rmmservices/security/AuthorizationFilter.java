package com.rmm.rmmservices.security;

import com.rmm.rmmservices.exceptions.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain
    ) throws IOException, ServletException {
        try {
            String header = request.getHeader(SecurityConstants.HEADER_NAME);

            if (header == null) {
                chain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken authentication = authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (AuthenticationException apiException) {
            LOGGER.warn(apiException.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }


    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if (token != null) {
            try{
                Claims user = Jwts.parser()
                        .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.KEY.getBytes()))
                        .parseClaimsJws(token)
                        .getBody();
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }else{
                    return  null;
                }
            } catch (Exception ex) {
                throw new AuthenticationException("Invalid user token", HttpStatus.UNAUTHORIZED);
            }
        }
        return null;
    }
}
