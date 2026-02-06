package data_structure.ratelimiter;

import java.time.Duration;

public class TestRateLimiter {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new FixWindowRateLimiter("test", 3, Duration.ofSeconds(2));
        rateLimiter.acquire();
        System.out.println("第一次 acquire 成功 " + System.nanoTime());
        rateLimiter.acquire();
        System.out.println("第二次 acquire 成功 " + System.nanoTime());
        rateLimiter.acquire();
        System.out.println("第三次 acquire 成功 " + System.nanoTime());
        rateLimiter.acquire();
        System.out.println("第四次 acquire 成功 " + System.nanoTime());
    }
}
