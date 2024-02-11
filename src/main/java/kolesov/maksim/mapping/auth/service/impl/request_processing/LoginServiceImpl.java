package kolesov.maksim.mapping.auth.service.impl.request_processing;

import kolesov.maksim.mapping.auth.config.ServiceConfig;
import kolesov.maksim.mapping.auth.dto.CredentialsDto;
import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.exception.UnauthorizedException;
import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import kolesov.maksim.mapping.auth.model.UserEntity;
import kolesov.maksim.mapping.auth.service.JwtService;
import kolesov.maksim.mapping.auth.service.repo.RedisService;
import kolesov.maksim.mapping.auth.service.repo.UserService;
import kolesov.maksim.mapping.auth.service.request_processing.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final String ERROR_MESSAGE = "User not found";

    private final UserService userService;
    private final JwtService jwtService;
    private final RedisService redisService;

    private final ServiceConfig config;

    @Override
    public TokenDto login(CredentialsDto credentials) throws UnauthorizedException {
        UserEntity user = userService.findByLogin(credentials.getLogin())
                .orElseThrow(() -> new UnauthorizedException(ERROR_MESSAGE));

        if (!BCrypt.checkpw(credentials.getPassword(), user.getPassword())) {
            throw new UnauthorizedException(ERROR_MESSAGE);
        }

        TokenDto.TokenDtoBuilder builder = TokenDto.builder();
        builder.access(jwtService.generate(user.getId().toString(), config.getAccessTtl()));

        String refresh = jwtService.generate(user.getId().toString(), config.getRefreshTtl());
        RefreshTokenEntity entity = RefreshTokenEntity.builder()
                .token(refresh)
                .userId(user.getId())
                .ttl(config.getRefreshTtl())
                .build();

        redisService.save(entity);
        builder.refresh(refresh);

        return builder.build();
    }

}
