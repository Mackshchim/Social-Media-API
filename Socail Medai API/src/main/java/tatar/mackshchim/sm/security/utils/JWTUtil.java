package tatar.mackshchim.sm.security.utils;

import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JWTUtil {

    Map<String, String> generateTokens(String subject, String issuer);

    Authentication buildAuthentication(String token);

}
