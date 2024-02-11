package kolesov.maksim.mapping.auth.service;

public interface JwtService {

    String generate(String sub, Long ttl);

    boolean verify(String token);

    String getSub(String token);

}
