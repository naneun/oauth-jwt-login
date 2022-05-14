package com.naneun.mall.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.naneun.mall.domain.entity.Member;
import com.naneun.mall.properties.JwtTokenProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.naneun.mall.auth.OAuthUtils.*;

@Component
public class JwtTokenProvider {

    private final String issuer;
    private final String secretKey;

    public JwtTokenProvider(JwtTokenProperties oAuthProperties) {
        issuer = oAuthProperties.getIssuer();
        secretKey = oAuthProperties.getSecretKey();
    }

    public String issueAccessToken(Member member) {
        return JWT.create()
                .withAudience(member.getId().toString())
                .withIssuer(issuer)

                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant()))

                .withClaim(USER_ID, member.getSocialId())
                .withClaim(RESOURCE_SERVER, member.getResourceServer())
                .withClaim(TOKEN_TYPE, ACCESS_TOKEN)

                .sign(Algorithm.HMAC256(secretKey));
    }

    public String issueRefreshToken(Member member) {
        return JWT.create()
                .withAudience(member.getId().toString())
                .withIssuer(issuer)

                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(LocalDateTime.now().plusWeeks(2L).atZone(ZoneId.systemDefault()).toInstant()))

                .withClaim(USER_ID, member.getSocialId())
                .withClaim(RESOURCE_SERVER, member.getResourceServer())
                .withClaim(TOKEN_TYPE, REFRESH_TOKEN)

                .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT decodeToken(String jwt) {
         return JWT.decode(jwt);
    }

    public DecodedJWT verifyToken(String jwt) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer(issuer)
                .build();

        return verifier.verify(jwt);
    }

    public Long getUserId(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim(USER_ID).asLong();
    }
}
