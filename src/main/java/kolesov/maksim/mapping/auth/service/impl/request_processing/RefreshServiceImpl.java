package kolesov.maksim.mapping.auth.service.impl.request_processing;

import kolesov.maksim.mapping.auth.config.ServiceConfig;
import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.exception.ServiceException;
import kolesov.maksim.mapping.auth.model.RefreshTokenEntity;
import kolesov.maksim.mapping.auth.service.JwtService;
import kolesov.maksim.mapping.auth.service.repo.RedisService;
import kolesov.maksim.mapping.auth.service.repo.UserRoleService;
import kolesov.maksim.mapping.auth.service.repo.UserService;
import kolesov.maksim.mapping.auth.service.request_processing.RefreshService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RedisService redisService;
    private final JwtService jwtService;

    private final ServiceConfig config;

    @Override
    public TokenDto refresh(TokenDto dto) throws ServiceException {
        String refreshToken = dto.getRefresh();

        if (!jwtService.verify(refreshToken)) {
            throw new ServiceException("Unable to verify token");
        }

        UUID id = UUID.fromString(jwtService.getSub(refreshToken));

        if (!userService.existById(id)) {
            throw new ServiceException("User not found");
        }

        TokenDto.TokenDtoBuilder builder = TokenDto.builder();
        List<String> roles = userRoleService.getRolesByUser(id).stream()
                        .map(e -> e.getRole().toString())
                        .toList();
        builder.access(jwtService.generate(id.toString(), roles, config.getAccessTtl(), true));

        Optional<String> cachedToken = redisService.findTokenByUserId(id);
        if (cachedToken.isPresent()) {
            if (!cachedToken.get().equals(refreshToken)) {
                throw new ServiceException("Unknown token");
            }

            builder.refresh(cachedToken.get());
        } else {
            String refresh = jwtService.generate(id.toString(), roles, config.getRefreshTtl(), false);
            builder.refresh(refresh);

            RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.builder()
                    .userId(id)
                    .token(refreshToken)
                    .ttl(config.getRefreshTtl())
                    .build();
            redisService.save(refreshTokenEntity);
        }

        return builder.build();
    }

}
