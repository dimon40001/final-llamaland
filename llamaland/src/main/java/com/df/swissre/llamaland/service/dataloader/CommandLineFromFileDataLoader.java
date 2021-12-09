package com.df.swissre.llamaland.service.dataloader;

import java.io.File;
import java.util.List;

import com.df.swissre.llamaland.utils.FileReader;

public class CommandLineFromFileDataLoader implements DataLoaderService {

    private int citizensCommandLineIndex = 0;
    private int emailsCommandLineIndex = 1;

    private final String[] commandLineArgs;
    private FileReader fileReader;

    public CommandLineFromFileDataLoader(final String[] args) {
        this.commandLineArgs = args;
    }

    @Override
    public List<String> loadEmails() {
        return getStrings(emailsCommandLineIndex);
    }

    @Override
    public List<String> loadCitizens() {
        return getStrings(citizensCommandLineIndex);
    }

    private List<String> getStrings(int commandlineParameterPosition) {
        File file = new File(commandLineArgs[commandlineParameterPosition]);
        return fileReader.readFile(file.toURI());
    }

    public void setCitizensCommandLineIndex(int citizensCommandLineIndex) {
        this.citizensCommandLineIndex = citizensCommandLineIndex;
    }

    public void setEmailsCommandLineIndex(int emailsCommandLineIndex) {
        this.emailsCommandLineIndex = emailsCommandLineIndex;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
