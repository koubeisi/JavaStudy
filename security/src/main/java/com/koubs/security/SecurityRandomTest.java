package com.koubs.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

/**
 * @author KouBeisi
 * @since 2021/7/6
 */
@Slf4j
public class SecurityRandomTest {

    @Test
    void test(){
        final SecureRandom secureRandom = new SecureRandom();
        final int i = secureRandom.nextInt(20);
        log.debug("{}",i);
    }
}
