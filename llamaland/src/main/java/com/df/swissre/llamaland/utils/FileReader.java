package com.df.swissre.llamaland.utils;

import java.net.URI;
import java.util.List;

public interface FileReader {
    List<String> readFile(URI filename);
}
