package kolesov.maksim.mapping.auth.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import kolesov.maksim.mapping.auth.exception.ServiceException;
import kolesov.maksim.mapping.auth.service.JwtService;
import kolesov.maksim.mapping.auth.service.KeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final KeyService keyService;

    @Value("${app.jwt.issuer}")
    private String issuer;

    @Override
    public String generate(String sub, Long ttl) {
        try {
            JWSSigner signer = new RSASSASigner(keyService.getPrivateKey());
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(sub)
                    .expirationTime(Date.from(Instant.now().plus(ttl, ChronoUnit.SECONDS)))
                    .issuer(issuer)
            .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
                    claimsSet
            );

            signedJWT.sign(signer);

            return signedJWT.serialize();

        } catch (ServiceException | JOSEException e) {
            log.error("Failed to generate token", e);
            return null;
        }
    }

    @Override
    public boolean verify(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) keyService.getPublicKey());

            return signedJWT.verify(verifier);
        } catch (ServiceException | ParseException | JOSEException e) {
            log.error("Failed to verify token", e);
            return false;
        }
    }

    @Override
    public String getSub(String token) {
        if (!verify(token)) {
            return null;
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            log.error("Failed to load subject from token", e);
            return null;
        }
    }

}
