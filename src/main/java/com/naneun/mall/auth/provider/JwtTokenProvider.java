package com.naneun.mall.auth.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.naneun.mall.auth.properties.JwtProperties;
import com.naneun.mall.domain.entity.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.naneun.mall.auth.utils.OAuthUtils.*;

@Component
public class JwtTokenProvider {

    private final String issuer;
    private final Algorithm algorithm;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        issuer = jwtProperties.getIssuer();
        algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
    }

    public String issueAccessToken(Member member) {
        return JWT.create()
                .withAudience(member.getId().toString())
                .withIssuer(issuer)

                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(1L).atZone(ZoneId.systemDefault()).toInstant()))

                .withClaim(SOCIAL_ID, member.getSocialId())
                .withClaim(RESOURCE_SERVER, member.getResourceServer().name())
                .withClaim(TOKEN_TYPE, ACCESS_TOKEN)

                .sign(algorithm);
    }

    public String issueRefreshToken(Member member) {
        return JWT.create()
                .withAudience(member.getId().toString())
                .withIssuer(issuer)

                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(LocalDateTime.now().plusWeeks(2L).atZone(ZoneId.systemDefault()).toInstant()))

                .withClaim(SOCIAL_ID, member.getSocialId())
                .withClaim(RESOURCE_SERVER, member.getResourceServer().name())
                .withClaim(TOKEN_TYPE, REFRESH_TOKEN)

                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String jwt) {
        return JWT.decode(jwt);
    }

    public DecodedJWT verifyToken(String jwt) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .acceptExpiresAt(5)
                .build();

        return verifier.verify(jwt);
    }

    public Long getUserId(DecodedJWT decodedJWT) {
        return Long.parseLong(decodedJWT.getAudience().get(0));
    }
}
