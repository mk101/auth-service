package kolesov.maksim.mapping.auth.controller;

import jakarta.validation.Valid;
import kolesov.maksim.mapping.auth.dto.CredentialsDto;
import kolesov.maksim.mapping.auth.dto.ResponseDto;
import kolesov.maksim.mapping.auth.dto.TokenDto;
import kolesov.maksim.mapping.auth.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseDto<TokenDto> login(@RequestBody @Valid CredentialsDto credentials);

    @PostMapping("/register")
    ResponseDto<String> register(@RequestBody @Valid UserDto userDto);

    @PostMapping("/refresh")
    ResponseDto<TokenDto> refresh(@RequestBody @Valid TokenDto tokenDto);

    @PostMapping("/logout")
    ResponseDto<Void> logout(@RequestBody @Valid TokenDto tokenDto);

}
