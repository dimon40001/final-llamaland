package com.df.swissre.llamaland.service.distribution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationTypeTest {

    @Test
    void testShortTermIntervalIs5Days() {
        assertEquals(5, NotificationType.SHORT_TERM.getValue());
    }

    @Test
    void testLongTermIntervalIs10Days() {
        assertEquals(10, NotificationType.LONG_TERM.getValue());
    }
}