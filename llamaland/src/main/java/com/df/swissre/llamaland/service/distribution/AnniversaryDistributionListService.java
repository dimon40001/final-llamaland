package com.df.swissre.llamaland.service.distribution;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.repository.CitizenRepository;
import com.df.swissre.llamaland.repository.EmailRepository;
import com.df.swissre.llamaland.service.validator.EmailValidator;

public class AnniversaryDistributionListService implements DistributionListService {

    private CitizenRepository citizenRepository;
    private EmailRepository optoutEmailRepository;
    private EmailValidator emailValidator;

    private int years = 100;
    private int thresholdCountForLongTermNotifications = 20;

    public AnniversaryDistributionListService() {
    }

    public AnniversaryDistributionListService(EmailRepository optoutEmailRepository, EmailValidator emailValidator, CitizenRepository citizenRepository, int thresholdCountForLongTermNotifications) {
        this.optoutEmailRepository = optoutEmailRepository;
        this.emailValidator = emailValidator;
        this.citizenRepository = citizenRepository;
        this.thresholdCountForLongTermNotifications = thresholdCountForLongTermNotifications;
    }

    @Override
    public List<Citizen> getDistributionListForDate(final LocalDate date) {
        final List<Email> blackListedEmails = buildNonRelevantEmailList();
        final List<Citizen> notifyShortTermList = new ArrayList<>();
        final List<Citizen> notifyLongTermList = new ArrayList<>();

        DateMatcher shortTermMatcher = new AnniversaryReminderDateMatcher(years, NotificationType.SHORT_TERM.getValue());
        DateMatcher longTermMatcher = new AnniversaryReminderDateMatcher(years, NotificationType.LONG_TERM.getValue());

        citizenRepository.getAll().stream()
                .filter(citizen -> emailValidator.isValid(citizen.getEmail()))
                .filter(citizen -> !blackListedEmails.contains(citizen.getEmail()))
                .forEach(citizen -> {
                    if (shortTermMatcher.matches(citizen.getBirthDate(), date)) {
                        notifyShortTermList.add(citizen);
                    } else if (longTermMatcher.matches(citizen.getBirthDate(), date)) {
                        notifyLongTermList.add(citizen);
                    }
                });

        final List<Citizen> finalList = new ArrayList<>();

        if (notifyShortTermList.size() <= thresholdCountForLongTermNotifications) {
            finalList.addAll(notifyShortTermList);
        }

        if (notifyLongTermList.size() > thresholdCountForLongTermNotifications) {
            finalList.addAll(notifyLongTermList);
        }

        return finalList;
    }

    private List<Email> buildNonRelevantEmailList() {
        Set<Email> ignoreEmails = new HashSet<>(optoutEmailRepository.getAll());
        ignoreEmails.addAll(citizenRepository.getNonUniqueEmails());
        return new ArrayList<>(ignoreEmails);
    }

    public void setOptoutEmailRepository(EmailRepository optoutEmailRepository) {
        this.optoutEmailRepository = optoutEmailRepository;
    }

    public void setCitizenRepository(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public void setThresholdCountForLongTermNotifications(int thresholdCountForLongTermNotifications) {
        this.thresholdCountForLongTermNotifications = thresholdCountForLongTermNotifications;
    }

    public void setEmailValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    public void setYears(int years) {
        this.years = years;
    }
}
