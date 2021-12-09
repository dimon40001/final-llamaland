package com.df.swissre.llamaland.entity;

import java.util.Objects;

public class Email {

    private String email;

    public String getEmail() {
        return email;
    }

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
