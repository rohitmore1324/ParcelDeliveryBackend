package com.example.doloApp.filter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.FirebaseAuthException;

import com.example.doloApp.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class FirebaseTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String idToken = header.substring(7);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                String email = decodedToken.getEmail();

                // ✅ Attach user details to request
                request.setAttribute("uid", uid);
                request.setAttribute("email", email);

                // ✅ Use Firebase custom claims for role (more secure)
                String role = "USER"; // Default role
                if (decodedToken.getClaims() != null && decodedToken.getClaims().containsKey("role")) {
                    role = (String) decodedToken.getClaims().get("role");
                }
                request.setAttribute("role", role);

                // ✅ Set authentication context with proper authorities
                List<String> authorities = Collections.singletonList("ROLE_" + role);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(uid, null, 
                            authorities.stream()
                                .map(auth -> new org.springframework.security.core.authority.SimpleGrantedAuthority(auth))
                                .toList());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (FirebaseAuthException e) {
                logger.error("Firebase token verification failed", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Firebase ID token.");
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing Authorization header.");
            return;
        }

        // ✅ Proceed with the next filter
        filterChain.doFilter(request, response);
    }
}
