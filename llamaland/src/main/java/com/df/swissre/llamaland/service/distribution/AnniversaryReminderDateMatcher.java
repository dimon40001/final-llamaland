package com.df.swissre.llamaland.service.distribution;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AnniversaryReminderDateMatcher implements DateMatcher {

    private final int yearsDelta;
    private final int workingDaysNotificationMargin;

    public AnniversaryReminderDateMatcher(int yearsDelta, int workingDaysNotificationMargin) {
        this.yearsDelta = yearsDelta;
        this.workingDaysNotificationMargin = workingDaysNotificationMargin;
    }

    @Override
    public boolean matches(final LocalDate birthDate, final LocalDate dateOfCheck) {
        LocalDate datePlusWorkdays = addWorkdays(dateOfCheck);
        return datePlusWorkdays.minusYears(yearsDelta).equals(birthDate);
    }

    private LocalDate addWorkdays(final LocalDate date) {
        int workDaysCount = workingDaysNotificationMargin;
        LocalDate realDate = date;
        while (workDaysCount > 0) {
            realDate = realDate.plusDays(1);
            DayOfWeek dayOfWeek = realDate.getDayOfWeek();
            if (!isWeekend(dayOfWeek)) {
                workDaysCount--;
            }
        }
        return realDate;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

}
