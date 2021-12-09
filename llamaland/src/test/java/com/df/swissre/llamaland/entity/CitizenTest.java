package com.df.swissre.llamaland.entity;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTest {

    @Test
    void whenFieldsAreSameThenCitizensAreEqual() {
        Email email = new Email("no-reply@example.com");
        LocalDate firstOfJanuary2000 = LocalDate.of(2000, Month.JANUARY, 01);

        Citizen citizen1 = new Citizen("Lastname", "Firstname", firstOfJanuary2000, email);
        Citizen citizen2 = new Citizen("Lastname", "Firstname", firstOfJanuary2000, email);

        assertEquals(citizen1, citizen2);
        assertEquals(citizen1.hashCode(), citizen2.hashCode());
    }

    @Test
    void whenFieldsAreDifferThenCitizensAreNotEqual() {
        LocalDate firstOfJanuary2000 = LocalDate.of(2000, Month.JANUARY, 1);
        LocalDate secondOfJanuary2000 = LocalDate.of(2000, Month.JANUARY, 2);

        Citizen citizen1 = new Citizen("Lastname", "Firstname", firstOfJanuary2000, new Email("no-reply@example.com"));
        Citizen citizen2 = new Citizen("Lastname", "Firstname", secondOfJanuary2000, new Email("no-reply@example.com"));

        assertNotEquals(citizen1, citizen2);
    }


}