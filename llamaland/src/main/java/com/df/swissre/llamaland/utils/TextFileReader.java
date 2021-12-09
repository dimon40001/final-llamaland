package com.df.swissre.llamaland.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.df.swissre.llamaland.exception.IORuntimeException;

public class TextFileReader implements FileReader {

    @Override
    public List<String> readFile(final URI filename) {
        try (Stream<String> fileLines = Files.lines(Paths.get(filename))) {
            List<String> lines = fileLines.collect(Collectors.toList());
            return lines;
        } catch (IOException e) {
            System.err.printf("ERROR: Problem reading file %s%n", filename);
            throw new IORuntimeException("Error with file");
        }
    }

}
