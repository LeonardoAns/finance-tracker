package com.financeTracker.finance.tracker.useCases.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.financeTracker.finance.tracker.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateTokenUseCase {

    @Value("${api.security.token.secret}")
    private String key;

    public String execute(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            var verifier = JWT.require(algorithm)
                    .withIssuer("finance-tracker")
                    .build();

            var decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();
            Long userId = decodedJWT.getClaim("userId").asLong();

            return email;
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Invalid Token");
        } catch (JWTVerificationException e) {
            throw new InvalidRequestException("Invalid Token");
        }
    }
}
