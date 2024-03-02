package kolesov.maksim.mapping.auth.service.impl.request_processing;

import kolesov.maksim.mapping.auth.service.JwtService;
import kolesov.maksim.mapping.auth.service.repo.RedisService;
import kolesov.maksim.mapping.auth.service.request_processing.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private JwtService jwtService;
    private RedisService redisService;

    @Override
    public void logout(String refreshToken) {
        UUID id = UUID.fromString(jwtService.getSub(refreshToken));
        redisService.delete(id);
    }

}
