package com.df.swissre.llamaland.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.df.swissre.llamaland.exception.IORuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextFileReaderTest {

    FileReader fileReader = new TextFileReader();

    @Test
    void noErrorsWhenReadingExistingFile() throws URISyntaxException {
        URL url = TextFileReader.class.getClassLoader().getResource("citizens.txt");
        URI uri = url.toURI();
        List<String> actual = fileReader.readFile(uri);
        assertTrue(actual.size() > 0);
    }

    @Test
    void nonExistingFileThrowsRuntimeException() {
        assertThrows(IORuntimeException.class, () -> {
            fileReader.readFile(new File("").toURI());
        });
    }

}