package kolesov.maksim.mapping.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@RedisHash("RefreshTokenEntity")
public class RefreshTokenEntity implements Serializable {

    @Id
    private UUID userId;

    private String token;

    @TimeToLive
    private Long ttl;

}
