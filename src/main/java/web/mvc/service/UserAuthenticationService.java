package web.mvc.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class UserAuthenticationService {

    private String rawToken = null;
    private String username;
    private Integer userId;
    private Integer firmId;

    public void setToken(String token) {
        this.rawToken = token.replaceFirst("Bearer ", "");

        final String[] user = new String[1];

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
                    .parseClaimsJws(rawToken)
                    .getBody();
        } catch (Exception e) {
            // no signing key on client. We trust that this JWT came from the server and has been verified there
        }
        this.username = user[0];
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean isLoggedIn() {
        return rawToken != null;
    }

    public void logout() {
        this.username = null;
        this.rawToken = null;
        this.firmId = -1;
    }

    public String getRawToken() {
        return this.rawToken;
    }

    public void setUserId(Integer userId){ this.userId=userId; }

    public Integer getUserId(){ return this.userId; }

    public void setFirmId(Integer firmId) { this.firmId = firmId; }

    public Integer getFirmId() { return this.firmId; }
}

