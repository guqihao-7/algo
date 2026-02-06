package data_structure.ratelimiter;


import java.time.Duration;

public class SlideWindowRateLimiter extends BaseRateLimiter {

    public SlideWindowRateLimiter(String name, int rate, Duration duration) {
        super(name, rate, duration);
    }

    String lua = """
                local key = KEYS[1]
                local limit = tonumber(ARGV[1])
                local window = tonumber(ARGV[2])
                local acquire = tonumber(ARGV[3])
                local now = tonumber(ARGV[4])
                local uuid = ARGV[5]
            
                local member = acquire .. '#' .. uuid
            
                redis.call('ZREMRANGEBYSCORE', key, 0, now - window)
            
                local currentCount = 0
                local members = redis.call('ZRANGE', key, 0, -1)
            
                for _, val in ipairs(members) do
                    local storedAcquire = tonumber(string.sub(val, 1, string.find(val, '#', 1, true) - 1))
                    if storedAcquire then
                        currentCount = currentCount + storedAcquire
                    end
                end
            
                if currentCount + acquire > limit then
                    local oldest_entry = redis.call('ZRANGE', key, 0, 0, 'WITHSCORES')
                    if oldest_entry and #oldest_entry > 0 then
                        local firstScore = tonumber(oldest_entry[2])
                        return firstScore + window - now
                    end
                else
                    redis.call('zadd', key, now, member)
                    redis.call('pexpire', key, window + 1000)
                end
                return 0
            """;

    @Override
    protected String getLuaScript() {
        return lua;
    }

    @Override
    protected Object[] getArgs() {
        return null;
    }
}
