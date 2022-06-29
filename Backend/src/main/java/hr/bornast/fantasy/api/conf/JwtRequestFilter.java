package hr.bornast.fantasy.api.conf;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.bornast.fantasy.application.service.impl.AuthServiceImpl;
import hr.bornast.fantasy.common.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthServiceImpl jwtService;

    // TODO: refactor this method
    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String jwtToken = null;
        String username = null;
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);

            try {
                username = jwtUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT token");
            } catch (ExpiredJwtException e) {
                System.out.println("Jwt token is expired");
            }

        } else {
            System.out.println("Jwt token does not start with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = jwtService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                var usernamePasswordToken = new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());

                usernamePasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordToken);
            }
        }

        filterChain.doFilter(request, response);

    }

}
