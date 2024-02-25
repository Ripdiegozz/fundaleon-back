package com.fundaleonREST.fundaleonapirest.configuration;

import com.fundaleonREST.fundaleonapirest.model.User;
import com.fundaleonREST.fundaleonapirest.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException {
        try {
            final UUID authHeader = UUID.fromString(request.getHeader("Authorization"));
            User userAuthorized = this.userService.getUserById(authHeader);
            System.out.println(userAuthorized);

            // Unfiltered routes
            String[] paths = {"/book/get/", "/customer/get/all/count", "/customer/get/count/month", "/user/get/"};
            for (String path : paths) {
                if (request.getRequestURI().contains(path)) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            if (userAuthorized.getRole().contains("ADMIN")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
