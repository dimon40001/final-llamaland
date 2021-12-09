package com.df.swissre.llamaland.service.distribution;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.df.swissre.llamaland.CitizenHelper;
import com.df.swissre.llamaland.EmailHelper;
import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.repository.CitizenRepository;
import com.df.swissre.llamaland.repository.EmailRepository;
import com.df.swissre.llamaland.service.validator.RFC5322EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnniversaryDistributionListServiceTest {

    AnniversaryDistributionListService birthdayNotificationService;

    @BeforeEach
    void setUp() {
        birthdayNotificationService = new AnniversaryDistributionListService();
        birthdayNotificationService.setEmailValidator(new RFC5322EmailValidator());
        birthdayNotificationService.setOptoutEmailRepository(new EmailRepository() {
            @Override
            public List<Email> getAll() {
                return EmailHelper.getOptOutEmails().stream().map(Email::new).collect(Collectors.toList());
            }
        });
        birthdayNotificationService.setCitizenRepository(new CitizenRepository() {
            @Override
            public List<Citizen> getAll() {
                return CitizenHelper.getFileCitizens();
            }

            @Override
            public List<Email> getNonUniqueEmails() {
                return Arrays.asList(new Email("bobby.brown@ilovellamaland.com"));
            }
        });
    }

    @Test
    void testOnlyShortTermAreSelectedWhenLongTermIsLessEqual20() {
        final LocalDate checkDate = LocalDate.of(2021, 3, 3);
        List<Citizen> distributionListForDate = birthdayNotificationService.getDistributionListForDate(checkDate);
        assertEquals(1, distributionListForDate.size());
    }

    @Test
    void testShortAndLongTermCitizensAreSelectedWhenLongTermIsMore20() {
        final LocalDate checkDate = LocalDate.of(2021, 7, 24);
        List<Citizen> distributionListForDate = birthdayNotificationService.getDistributionListForDate(checkDate);
        assertEquals(22, distributionListForDate.size());
    }

    @Test
    void testLongTermCitizensAreNotNotifiedInShortTerm() {
        final LocalDate checkDate = LocalDate.of(2021, 7, 30);
        List<Citizen> distributionListForDate = birthdayNotificationService.getDistributionListForDate(checkDate);
        assertEquals(0, distributionListForDate.size());
    }
}