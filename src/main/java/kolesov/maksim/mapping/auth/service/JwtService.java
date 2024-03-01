package kolesov.maksim.mapping.auth.service;

import java.util.List;

public interface JwtService {

    String generate(String sub, List<String> roles, Long ttl, boolean access);

    boolean verify(String token);

    String getSub(String token);

}
