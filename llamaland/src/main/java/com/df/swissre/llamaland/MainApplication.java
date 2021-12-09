package com.df.swissre.llamaland;

import java.time.LocalDate;
import java.util.List;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.repository.CitizenInMemoryRepository;
import com.df.swissre.llamaland.repository.EmailInMemoryRepository;
import com.df.swissre.llamaland.service.dataloader.CommandLineFromFileDataLoader;
import com.df.swissre.llamaland.service.distribution.AnniversaryDistributionListService;
import com.df.swissre.llamaland.service.distribution.DistributionListService;
import com.df.swissre.llamaland.service.notification.ConsoleNotificationService;
import com.df.swissre.llamaland.service.notification.NotificationService;
import com.df.swissre.llamaland.service.validator.RFC5322EmailValidator;
import com.df.swissre.llamaland.utils.TextFileReader;

public class MainApplication {
    private static final String HELP_MESSAGE = "Usage: provide command line parameters for CITIZENS and OPT-OUT EMAILS";
    private static String[] commandLineArgs;
    private static MainApplication mainApplication;

    private DistributionListService distributionListService;
    private NotificationService<Citizen> notificationService;


    public static void main(String[] args) {
        commandLineArgs = args;
        configure();
        mainApplication.run();
    }

    private static void configure() {
        mainApplication = new MainApplication();

        CommandLineFromFileDataLoader dataLoaderService = new CommandLineFromFileDataLoader(commandLineArgs);
        dataLoaderService.setFileReader(new TextFileReader());

        CitizenInMemoryRepository citizenRepository = new CitizenInMemoryRepository();
        EmailInMemoryRepository emailRepository = new EmailInMemoryRepository();

        citizenRepository.setDataLoaderService(dataLoaderService);
        emailRepository.setDataLoaderService(dataLoaderService);
        emailRepository.setEmailValidator(new RFC5322EmailValidator());

        AnniversaryDistributionListService anniversaryDistributionListService = new AnniversaryDistributionListService();
        anniversaryDistributionListService.setOptoutEmailRepository(emailRepository);
        anniversaryDistributionListService.setCitizenRepository(citizenRepository);
        anniversaryDistributionListService.setEmailValidator(new RFC5322EmailValidator());

        mainApplication.setDistributionListService(anniversaryDistributionListService);
        mainApplication.setNotificationService(new ConsoleNotificationService<>());
    }

    private void validateCommandLineParameters(String[] args) {
        if (args.length < 2) {
            System.out.println(HELP_MESSAGE);
            System.exit(0);
        }
    }

    private void run() {
        validateCommandLineParameters(commandLineArgs);
        LocalDate today = LocalDate.now();
        List<Citizen> distributionListForDate = distributionListService.getDistributionListForDate(today);
        notificationService.notify(distributionListForDate);
    }

    public void setDistributionListService(DistributionListService distributionListService) {
        this.distributionListService = distributionListService;
    }

    public void setNotificationService(NotificationService<Citizen> notificationService) {
        this.notificationService = notificationService;
    }
}
