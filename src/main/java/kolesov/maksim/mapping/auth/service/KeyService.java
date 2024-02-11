package kolesov.maksim.mapping.auth.service;

import kolesov.maksim.mapping.auth.exception.ServiceException;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyService {

    PublicKey getPublicKey()  throws ServiceException;

    PrivateKey getPrivateKey()  throws ServiceException;

}
