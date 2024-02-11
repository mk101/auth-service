package kolesov.maksim.mapping.auth.controller.impl;

import kolesov.maksim.mapping.auth.controller.KeyController;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RsaKeyController implements KeyController {

    @Value("${app.keys.public-path}")
    private Resource key;

    @Override
    @SneakyThrows
    public ByteArrayResource getPublicKey() {
        return new ByteArrayResource(key.getContentAsByteArray());
    }

}
