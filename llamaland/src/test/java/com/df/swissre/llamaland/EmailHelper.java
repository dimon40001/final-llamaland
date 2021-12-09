package com.df.swissre.llamaland;

import java.util.Arrays;
import java.util.List;

public class EmailHelper {

    public static List<String> getValidAndInvalidEmails() {
        return Arrays.asList("abc@example.com", "invalid_email", "other@example.com");
    }

    public static List<String> getOptOutEmails() {
        return Arrays.asList(
                "abc@example.com",
                "xyz@example.com",
                "betsy@heyitsme.com",
                "randomperson@llama.land");

    }

}
