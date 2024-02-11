package kolesov.maksim.mapping.auth.service.request_processing;

import kolesov.maksim.mapping.auth.dto.UserDto;
import kolesov.maksim.mapping.auth.exception.ServiceException;

public interface RegisterService {

    void register(UserDto userDto) throws ServiceException;

}
