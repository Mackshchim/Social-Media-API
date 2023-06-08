package tatar.mackshchim.sm.security.utils.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import tatar.mackshchim.sm.models.User;
import tatar.mackshchim.sm.security.details.UserDetailsImpl;
import tatar.mackshchim.sm.security.utils.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtilAuth0Impl implements JWTUtil {


    private static final long ACCESS_TOKEN_EXPIRES_TIME = 5 * 60 * 1000;  // FIVE MINUTES
    private static final long REFRESH_TOKEN_EXPIRES_TIME = 10 * 60 * 1000;  //30 MINUTES

    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Map<String, String> generateTokens(String subject, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));

        String accessToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRES_TIME))
                .withIssuer(issuer)
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES_TIME))
                .withIssuer(issuer)
                .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();

        tokens.put(ACCESS_TOKEN, accessToken);
        tokens.put(REFRESH_TOKEN, refreshToken);

        return tokens;
    }

    @Override
    public Authentication buildAuthentication(String token) {
        String parsedToken = parseToken(token);

        UserDetails userDetails = new UserDetailsImpl(User.builder()
                .email(parsedToken)
                .build());

        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                null);
    }

    private String parseToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }
}
