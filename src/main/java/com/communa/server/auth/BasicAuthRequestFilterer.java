//package com.communa.server.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Base64;
//
//@Component
//public class BasicAuthRequestFilterer extends BasicAuthenticationFilter {
//
//    public BasicAuthRequestFilterer(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        if(!request.getAuthType().equalsIgnoreCase(HttpServletRequest.BASIC_AUTH)) {
//
//        }
//
//        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        String[] headerParts = authHeader.split(" ");
//        if(headerParts.length != 2) {
//
//        }
//        String token = headerParts[1];
//        String[] emailPassword = new String(Base64.getDecoder().decode(token)).split(":");
//        if(emailPassword.length != 2) {
//
//        }
//        String email = emailPassword[0];
//        String password = emailPassword[1];
//    }
//}
