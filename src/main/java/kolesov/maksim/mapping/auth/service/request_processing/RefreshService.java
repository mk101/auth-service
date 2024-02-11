package kolesov.maksim.mapping.auth.service.request_processing;

import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.exception.ServiceException;

public interface RefreshService {

    TokenDto refresh(TokenDto dto) throws ServiceException;

}
