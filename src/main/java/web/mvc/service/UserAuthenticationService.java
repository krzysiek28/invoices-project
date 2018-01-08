package web.mvc.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class UserAuthenticationService {

    private String rawToken = null;
    private String username;

    public void setToken(String token) {
        token.replaceFirst("Bearer ", "");
        final String[] user = new String[1];
        this.rawToken = token;
        SigningKeyResolver signingKeyResolver = new SigningKeyResolverAdapter() {
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                user[0] = claims.getSubject();
                return null; // will throw exception, can be caught in caller
            }
        };

        try {
            Jwts.parser()
                    .setSigningKeyResolver(signingKeyResolver)
                    .parseClaimsJws(token)
                    .getBody();
            this.username = user[0];
        } catch (Exception e) {
            // no signing key on client. We trust that this JWT came from the server and has been verified there
        }
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean isLoggedIn() {
        return rawToken != null;
    }

    public void logout() {
        this.rawToken = null;
    }

    public String getRawToken() {
        return this.rawToken;
    }
}

