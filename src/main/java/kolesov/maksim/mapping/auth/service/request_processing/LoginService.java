package kolesov.maksim.mapping.auth.service.request_processing;

import kolesov.maksim.mapping.auth.dto.CredentialsDto;
import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.exception.UnauthorizedException;

public interface LoginService {

    TokenDto login(CredentialsDto credentials) throws UnauthorizedException;

}
