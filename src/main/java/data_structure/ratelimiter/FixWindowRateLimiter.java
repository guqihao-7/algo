package data_structure.ratelimiter;

import java.time.Duration;

/**
 * 固定窗口限流/计数器
 */
public class FixWindowRateLimiter extends BaseRateLimiter {

    public FixWindowRateLimiter(String name, int rate, Duration duration) {
        super(name, rate, duration);
    }

    String lua = """
            local key = KEYS[1]
            local limit = tonumber(ARGV[1])
            
            -- window unit is millisecond
            local window = tonumber(ARGV[2])
            local acquire = tonumber(ARGV[3])
            
            -- first check whether it is existed
            -- if not exist then set it with expire time
            if redis.call('set', key, acquire, 'PX', window, 'NX') then
                return 0
            end
            
            -- 2. key already exists
            local current = tonumber(redis.call('get', key) or 0)
            
            -- 3. check whether it is full
            if current >= limit then
                return redis.call('pttl', key)
            end
            
            -- 4. increase it
            redis.call('incrby', key, acquire)
            
            return 0
            """;

    @Override
    protected String getLuaScript() {
        return lua;
    }
}
