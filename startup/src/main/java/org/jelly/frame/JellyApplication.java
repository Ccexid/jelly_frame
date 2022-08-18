package org.jelly.frame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangxb
 */
@SpringBootApplication
@Slf4j
public class JellyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JellyApplication.class, args);
    }
}
