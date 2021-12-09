package com.df.swissre.llamaland.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.df.swissre.llamaland.entity.Email;
import com.df.swissre.llamaland.service.dataloader.DataLoaderService;
import com.df.swissre.llamaland.service.validator.EmailValidator;

public class EmailInMemoryRepository implements EmailRepository {

    private EmailValidator emailValidator;
    private DataLoaderService dataLoaderService;

    private List<Email> emails;


    @Override
    public List<Email> getAll() {
        if (emails == null) {

            emails = dataLoaderService.loadEmails().stream()
                    .map(Email::new)
                    .filter(email -> emailValidator.isValid(email))
                    .collect(Collectors.toList());
        }
        return emails;
    }

    public void setEmailValidator(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    public void setDataLoaderService(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
    }
}
