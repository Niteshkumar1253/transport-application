package com.capstone.favouriteService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTValidationFilter extends GenericFilterBean {
    public static final String OPTIONS = "OPTIONS";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    public static final String MYSTRONGKEY = "secret";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        if(request.getMethod().equals(OPTIONS)){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            final String authHeader = request.getHeader(AUTHORIZATION);
            log.info("Auth header: {}",authHeader);

            if(authHeader == null || !authHeader.startsWith(BEARER)){
                throw new ServletException("Auth Header Missing");
            }else{
                // below line is to fetch jwt from "Bearer jwt"
                // start index of jwt is 7
                final String token = authHeader.substring(7);

                final Claims payload;
                try {
                    payload = Jwts.parser()
                            .setSigningKey(MYSTRONGKEY)
                            .parseClaimsJws(token)
                            .getBody();
                    // epiration details, email all will be in payload
                } catch (JwtException e) {
                    logger.error("Token invalid",e);
                    throw new ServletException("Invalid token");
                }

                request.setAttribute("user",payload.getSubject());
                // in user service in subject we entered email id

            }
        }

        filterChain.doFilter(servletRequest,servletResponse);

        // process response
    }
}
