package com.iedaas.checklist;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public String authenticate(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        String user1 = null;
        if (token != null) {
            try {
                logger.trace("Decoding jwt token for fetching user uuid");
                user1 = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                logger.info("User uuid : " + user1);

            } catch (Exception e) {
                logger.error("Error caused while decoding the jwt Token");
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorised user access");
            }
        }
        return user1;
    }
}
