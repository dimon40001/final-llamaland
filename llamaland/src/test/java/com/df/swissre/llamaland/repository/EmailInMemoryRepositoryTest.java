package com.df.swissre.llamaland.repository;

import java.util.Arrays;
import java.util.List;

import com.df.swissre.llamaland.EmailHelper;
import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.service.dataloader.DataLoaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailInMemoryRepositoryTest {

    EmailInMemoryRepository emailInMemoryRepository;

    @BeforeEach
    void setUp() {
        emailInMemoryRepository = new EmailInMemoryRepository();
    }

    @Test
    void whenNoFilterThenAllEmailsAreLoadedToRepository() {
        emailInMemoryRepository.setEmailValidator((v) -> true);
        emailInMemoryRepository.setDataLoaderService(new DataLoaderService() {
            @Override
            public List<String> loadEmails() {
                return EmailHelper.getValidAndInvalidEmails();
            }

            @Override
            public List<String> loadCitizens() {
                return null;
            }
        });
        assertEquals(3, emailInMemoryRepository.getAll().size());
    }

    @Test
    void whenFilterIsProvidedThenItFiltersOutBadEmails() {
        emailInMemoryRepository.setEmailValidator((v) -> !v.getEmail().equals("invalid_email"));
        emailInMemoryRepository.setDataLoaderService(new DataLoaderService() {
            @Override
            public List<String> loadEmails() {
                return EmailHelper.getValidAndInvalidEmails();
            }

            @Override
            public List<String> loadCitizens() {
                return null;
            }
        });

        Email invalidEmail = new Email("invalid_email");
        List<Email> actualEmails = emailInMemoryRepository.getAll();

        assertAll(
                () -> assertEquals(2, actualEmails.size()),
                () -> assertFalse(actualEmails.contains(invalidEmail))
        );

    }

}