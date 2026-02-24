package com.example.user_profile_post_api.security.JWT_based_Authentication.jwt;
import java.io.IOException;

import com.example.user_profile_post_api.security.JWT_based_Authentication.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/*
JwtAuthenticationFilter শুধুমাত্র protected endpoint এর request এ চলে

✔️ তাই Login request এ কখনো JWT Filter run হয় না

না first time, না second time, না কোনো time।



JwtAuthenticationFilter steps:

Header থেকে token নেয়

Token valid কিনা check করে

Token থেকে username বের করে

Database থেকে user load করে

SecurityContext এ authentication set করে
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    /*
    //Constructor Dependency injection
    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
*/



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)//The JWT token contains a username and No user is currently authenticated in this request
        {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken); //এটা হলো Spring Security-কে জানিয়ে দেওয়া যে user authenticated।
            }
        }

        filterChain.doFilter(request, response); //doFilter() হচ্ছে filter chain এর ভিতর Request কে পরবর্তী filter এ পাঠানোর কাজ।
                                                 //Request → Filter 1 → Filter 2 → Filter 3 → Controller
    }
}
