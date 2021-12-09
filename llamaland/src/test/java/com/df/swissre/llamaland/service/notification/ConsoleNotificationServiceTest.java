package com.df.swissre.llamaland.service.notification;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleNotificationServiceTest {

    NotificationService<Citizen> notificationService;

    @BeforeEach
    void setUp() {
        notificationService = new ConsoleNotificationService<>();
    }

    @Test
    void whenDistributionListIsEmptyThenZeroCitizensNotified() {
        assertEquals(0, notificationService.notify(Collections.emptyList()));
    }

    @Test
    void whenDistributionListHas2ItemsThenTwoCitizensNotified() {
        List<Citizen> citizens = Arrays.asList(
                new Citizen("Citizen1", "", LocalDate.now(), new Email("")),
                new Citizen("Citizen2", "", LocalDate.now(), new Email("")));

        assertEquals(2, notificationService.notify(citizens));
    }
}