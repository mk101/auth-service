package kolesov.maksim.mapping.auth.controller.impl;

import kolesov.maksim.mapping.auth.controller.AuthController;
import kolesov.maksim.mapping.auth.dto.CredentialsDto;
import kolesov.maksim.mapping.auth.dto.ResponseDto;
import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.dto.UserDto;
import kolesov.maksim.mapping.auth.service.request_processing.LoginService;
import kolesov.maksim.mapping.auth.service.request_processing.RefreshService;
import kolesov.maksim.mapping.auth.service.request_processing.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final LoginService loginService;
    private final RegisterService registerService;
    private final RefreshService refreshService;

    @Override
    @SneakyThrows
    public ResponseDto<TokenDto> login(CredentialsDto credentials) {
        return new ResponseDto<>(loginService.login(credentials));
    }

    @Override
    @SneakyThrows
    public ResponseDto<String> register(UserDto userDto) {
        registerService.register(userDto);

        return new ResponseDto<>("User registered");
    }

    @Override
    @SneakyThrows
    public ResponseDto<TokenDto> refresh(TokenDto tokenDto) {
        return new ResponseDto<>(refreshService.refresh(tokenDto));
    }

}
