package com.df.swissre.llamaland.service.notification;

import java.util.List;
import java.util.Objects;

public class ConsoleNotificationService<T> implements NotificationService<T> {

    @Override
    public int notify(final List<T> recipients) {
        Objects.requireNonNull(recipients);
        if (recipients.isEmpty()) {
            System.out.println("No Citizens were found");
        } else {
            System.out.println("List of people the King should prepare an e-mail for:");
            for (T recipient : recipients) {
                System.out.println(recipient);
            }
        }
        return recipients.size();
    }
}
