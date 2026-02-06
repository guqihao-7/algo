package data_structure.ratelimiter;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

public class RedissonConfig {

    private static final String REDIS_ADDRESS = "redis://180.76.232.96:6379";

    private static volatile RedissonClient redissonClient;

    public static RedissonClient getInstance() {
        if (redissonClient == null) {
            synchronized (RedissonConfig.class) {
                if (redissonClient == null) {
                    Config config = new Config();
                    SingleServerConfig singleServerConfig = config.useSingleServer();
                    singleServerConfig.setAddress(REDIS_ADDRESS);
                    singleServerConfig.setPassword("GUQIhao123.");
                    redissonClient = Redisson.create(config);
                    return redissonClient;
                }
            }
        }
        return redissonClient;
    }
}