package com.df.swissre.llamaland.repository;

import java.util.List;

import com.df.swissre.llamaland.CitizenHelper;
import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.service.dataloader.DataLoaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenInMemoryRepositoryTest {

    CitizenInMemoryRepository citizenInMemoryRepository;

    @BeforeEach
    void setUp() {
        citizenInMemoryRepository = new CitizenInMemoryRepository();
    }

    @Test
    void whenCitizensWithInvalidBirthDateOrInvalidFormatThenRepositorySkipsThem() {
        citizenInMemoryRepository.setDataLoaderService(new DataLoaderService() {
            @Override
            public List<String> loadEmails() {
                return null;
            }

            @Override
            public List<String> loadCitizens() {
                return CitizenHelper.getValidAndInvalidCitizens();
            }
        });

        List<Citizen> citizens = citizenInMemoryRepository.getAll();
        assertEquals(4, citizens.size());

    }

    @Test
    void whenTwoCitizensHasSameEmailThenRepositoryIdentifiesThis() {
        citizenInMemoryRepository.setDataLoaderService(new DataLoaderService() {
            @Override
            public List<String> loadEmails() {
                return null;
            }

            @Override
            public List<String> loadCitizens() {
                return CitizenHelper.getValidAndInvalidCitizens();
            }
        });

        List<Email> emails = citizenInMemoryRepository.getNonUniqueEmails();

        assertEquals(1, emails.size());
    }
}