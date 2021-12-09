package com.df.swissre.llamaland.service.distribution;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnniversaryReminderDateMatcherTest {

    DateMatcher dateMatcher;
    private final int yearsDifference = 99;

    @Test
    void testVerySameDayInPastMatches() {
        dateMatcher = new AnniversaryReminderDateMatcher(yearsDifference, 0);
        LocalDate birthDate = LocalDate.of(1921, 7, 24);
        LocalDate checkMatchDate = birthDate.plusYears(yearsDifference);
        LocalDate checkNonMatchDate = birthDate.plusYears(yearsDifference).plusDays(1);

        assertTrue(dateMatcher.matches(birthDate, checkMatchDate));
        assertFalse(dateMatcher.matches(birthDate, checkNonMatchDate));
    }

    @Test
    void testWorkDaysMarginIsConsideredWhenMatching() {
        dateMatcher = new AnniversaryReminderDateMatcher(yearsDifference, 5);
        int year = 1921;
        LocalDate birthDate = LocalDate.of(year, 7, 29);
        LocalDate checkDate = LocalDate.of(year + yearsDifference, 7, 22);

        assertTrue(dateMatcher.matches(birthDate, checkDate));
    }

}