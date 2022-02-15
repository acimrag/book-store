package com.buythebook.bookstore.userservice.security;

import com.buythebook.bookstore.userservice.service.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;
    @Autowired
    private UserDetailService userDetailService;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private static final String BEARER = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (tokenHeader != null && tokenHeader.startsWith(BEARER + " ")) {
                String token = tokenHeader.substring(7);
                if (jwtTokenGenerator.validateToken(token)) {
                    String userName = jwtTokenGenerator.getUserName(token);
                    UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                            null,
                            userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error("User auth failed", e);
        }
        filterChain.doFilter(request, response);
    }

}
