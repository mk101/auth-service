package kolesov.maksim.mapping.auth.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface KeyController {

    @GetMapping(value = "/public", produces = "application/octet-stream")
    ByteArrayResource getPublicKey();

}
