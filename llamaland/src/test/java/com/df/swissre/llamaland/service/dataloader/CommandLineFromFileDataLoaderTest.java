package com.df.swissre.llamaland.service.dataloader;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import com.df.swissre.llamaland.CitizenHelper;
import com.df.swissre.llamaland.EmailHelper;
import com.df.swissre.llamaland.utils.FileReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineFromFileDataLoaderTest {

    private final CommandLineFromFileDataLoader commandLineFromFileDataLoader =  new CommandLineFromFileDataLoader(new String[]{"param0", "param1"});

    @Test
    void testCitizensAreLoadedFromParameter0() {
        commandLineFromFileDataLoader.setFileReader(new FileReader() {
            @Override
            public List<String> readFile(URI filename) {
                if (filename.toString().endsWith("param0")) {
                    return CitizenHelper.getValidAndInvalidCitizens();
                } else {
                    return Collections.emptyList();
                }
            }
        });

        assertFalse(commandLineFromFileDataLoader.loadCitizens().isEmpty());
    }

    @Test
    void testEmailsAreLoadedFromParameter1() {
        commandLineFromFileDataLoader.setFileReader(new FileReader() {
            @Override
            public List<String> readFile(URI filename) {
                if (filename.toString().endsWith("param1")) {
                    return EmailHelper.getValidAndInvalidEmails();
                } else {
                    return Collections.emptyList();
                }
            }
        });

        assertFalse(commandLineFromFileDataLoader.loadEmails().isEmpty());
    }
}