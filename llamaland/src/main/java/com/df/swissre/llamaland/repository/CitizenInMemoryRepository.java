package com.df.swissre.llamaland.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.service.dataloader.DataLoaderService;

public class CitizenInMemoryRepository implements CitizenRepository {

    private DataLoaderService dataLoaderService;

    private List<Citizen> citizens;

    protected Optional<Citizen> unmarshall(String s) {
        String[] tokens = s.split(",");
        if (tokens.length != 4) {
            System.err.println("Record not loaded (invalid record format): " + s);
            return Optional.empty();
        }

        LocalDate birthdate = null;
        try {
            birthdate = LocalDate.parse(tokens[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.err.println("Record not loaded (invalid birthdate): " + s);
            return Optional.empty();
        }
        String lastName = tokens[0];
        String firstName = tokens[1];
        Email email = new Email(tokens[3]);
        return Optional.of(new Citizen(lastName, firstName, birthdate, email));
    }

    @Override
    public List<Citizen> getAll() {
        if (citizens == null) {
            citizens = dataLoaderService.loadCitizens().stream()
                    .map(this::unmarshall)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return citizens;
    }

    @Override
    public List<Email> getNonUniqueEmails() {
        Set<Email> nonUniqueEmails = new HashSet<>();
        Set<Email> uniqueEmailCandidates = new HashSet<>();
        for (Citizen citizen : getAll()) {
            Email email = citizen.getEmail();
            if (uniqueEmailCandidates.contains(email)) {
                nonUniqueEmails.add(email);
            } else {
                uniqueEmailCandidates.add(email);
            }
        }
        return new ArrayList<>(nonUniqueEmails);
    }

    public void setDataLoaderService(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }
}
