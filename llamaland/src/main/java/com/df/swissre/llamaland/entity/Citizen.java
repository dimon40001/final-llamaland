package com.df.swissre.llamaland.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Citizen {

    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Email email;

    public Citizen(String lastName, String firstName, LocalDate birthDate, Email email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Citizen{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizen)) return false;
        Citizen citizen = (Citizen) o;
        return Objects.equals(lastName, citizen.lastName) && Objects.equals(firstName, citizen.firstName) && Objects.equals(birthDate, citizen.birthDate) && Objects.equals(email, citizen.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, birthDate, email);
    }
}
