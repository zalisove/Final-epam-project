package com.zalisove;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class HashingTest {

    @Test
    void hash() throws NoSuchAlgorithmException {
        assertEquals("7C222FB2927D828AF22F592134E8932480637C0D",Hashing.hash("12345678","SHA-1"));
    }
    @Test
    void hashThrowException() {
        assertThrows(NoSuchAlgorithmException.class, () -> {
            Hashing.hash("12345678","XXX");
        });
    }
}