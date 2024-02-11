package kolesov.maksim.mapping.auth.service.impl;

import kolesov.maksim.mapping.auth.exception.ServiceException;
import kolesov.maksim.mapping.auth.service.KeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
@Service
public class RsaKeyService implements KeyService {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Value("${app.keys.public-path}")
    private Resource publicResource;
    @Value("${app.keys.private-path}")
    private Resource privateResource;

    @Override
    public PublicKey getPublicKey() throws ServiceException {
        if (publicKey == null) {
            initKeys();
        }

        return publicKey;
    }

    @Override
    public PrivateKey getPrivateKey() throws ServiceException {
        if (privateKey == null) {
            initKeys();
        }

        return privateKey;
    }

    private void initKeys() throws ServiceException {
        try {
            initPublic();
            initPrivate();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private void initPublic() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Load public key from: {}", publicResource.getURL().getPath());
        byte[] bytes = publicResource.getContentAsByteArray();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        publicKey = factory.generatePublic(spec);
    }

    private void initPrivate() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        log.info("Load private key from: {}", privateResource.getURL().getPath());
        byte[] bytes = privateResource.getContentAsByteArray();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");

        privateKey = factory.generatePrivate(spec);
    }

}
