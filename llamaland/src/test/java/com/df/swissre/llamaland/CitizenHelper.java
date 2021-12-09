package com.df.swissre.llamaland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;

public class CitizenHelper {

    public static List<String> getValidAndInvalidCitizens() {
        return Arrays.asList(
                "Musterman-bad-email,Max1,10-11-1950,max1@example.com",
                "Musterman-bad-birthdate,Max2,13-13-1950,bobby.brown@ilovellamaland.com",
                "Musterman-duplicate-email,Max3,28-02-1900,max3@heyitsme.com",
                "Musterman-duplicate-email,Max4,01-01-1920,max3@heyitsme.com",
                "Musterman-good,Max5,01-01-2000,max5@ilovellamaland.com",
                "invalid line"
        );
    }

    private static Citizen unmarshallCitizen(String str) {
        String[] tokens = str.split(",");
        return new Citizen(tokens[0], tokens[1], LocalDate.parse(tokens[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")), new Email(tokens[3]));
    }

    public static List<Citizen> getFileCitizens() {
        return Stream.of(
                // TEST FILE FOR CHECK ON 2021-07-24
                // NO - birthday doesn't match for 2021-07-24
                // YES - for check on 2021-03-03
                "Von Tappo,Alfredo,10-03-1921,alexacie@vontappo.llama.land",

                // NO - wrong email"
                "Doe,John,30-07-1921,john@doe",

                // NO - duplicate email
                "Brown,Bobby,30-07-1921,bobby.brown@ilovellamaland.com",
                "Brown,Dobby,30-07-1921,bobby.brown@ilovellamaland.com",

                // NO - OPTED OUT"
                "Mustermann,Betsy,30-07-1921,betsy@heyitsme.com",

                // YES - must be notified for 5-days check on 2021-07-24",
                "O'Rourke,Betsy,30-07-1921,betsy_o_rourke@heyitsme.com",

                // YES - must be notified for 10-days check on 2021-07-24
                // AND SHOULD NOT BE notified in 5 days check (check date 2021-07-30)
                "Green01,Kate,06-08-1921,user01@example.com",
                "Green02,Kate,06-08-1921,user02@example.com",
                "Green03,Kate,06-08-1921,user03@example.com",
                "Green04,Kate,06-08-1921,user04@example.com",
                "Green05,Kate,06-08-1921,user05@example.com",
                "Green06,Kate,06-08-1921,user06@example.com",
                "Green07,Kate,06-08-1921,user07@example.com",
                "Green08,Kate,06-08-1921,user08@example.com",
                "Green09,Kate,06-08-1921,user09@example.com",
                "Green10,Kate,06-08-1921,user10@example.com",
                "Green11,Kate,06-08-1921,user11@example.com",
                "Green12,Kate,06-08-1921,user12@example.com",
                "Green13,Kate,06-08-1921,user13@example.com",
                "Green14,Kate,06-08-1921,user14@example.com",
                "Green15,Kate,06-08-1921,user15@example.com",
                "Green16,Kate,06-08-1921,user16@example.com",
                "Green17,Kate,06-08-1921,user17@example.com",
                "Green18,Kate,06-08-1921,user18@example.com",
                "Green19,Kate,06-08-1921,user19@example.com",
                "Green20,Kate,06-08-1921,user20@example.com",
                "Green21,Kate,06-08-1921,user21@example.com",
                "Marz,Anna01,17-03-1921,anna01@test.com",
                "Marz,Anna02,17-03-1921,anna02@test.com",
                "Marz,Anna03,17-03-1921,anna03@test.com",
                "Marz,Anna04,17-03-1921,annazc04@test.com"
        )
                .map(CitizenHelper::unmarshallCitizen)
                .collect(Collectors.toList());
    }
}
