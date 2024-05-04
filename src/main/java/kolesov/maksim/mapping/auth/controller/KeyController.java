package kolesov.maksim.mapping.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface KeyController {

    @Tags(value = @Tag(name = "Auth"))
    @GetMapping(value = "/public", produces = "application/octet-stream")
    ByteArrayResource getPublicKey();

}
