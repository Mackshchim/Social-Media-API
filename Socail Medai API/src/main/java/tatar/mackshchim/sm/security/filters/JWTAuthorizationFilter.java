package tatar.mackshchim.sm.security.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tatar.mackshchim.sm.security.utils.AuthorizationHeaderUtil;
import tatar.mackshchim.sm.security.utils.JWTUtil;

import java.io.IOException;

import static tatar.mackshchim.sm.security.filters.JWTAuthenticationFilter.AUTHENTICATION_URL;

@RequiredArgsConstructor
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTHENTICATION_URL)) {
            filterChain.doFilter(request,response);
        } else {
            if (authorizationHeaderUtil.hasAuthorizationToken(request)) {

                String jwt = authorizationHeaderUtil.getToken(request);

                try {
                    Authentication authentication = jwtUtil.buildAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (JWTVerificationException e) {
                    logger.info(e.getMessage());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }

        }
    }
}
