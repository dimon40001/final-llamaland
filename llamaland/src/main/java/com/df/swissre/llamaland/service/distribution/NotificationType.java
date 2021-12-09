package com.df.swissre.llamaland.service.distribution;

public enum NotificationType {
    SHORT_TERM(5), LONG_TERM(10);

    private int value;

    NotificationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
