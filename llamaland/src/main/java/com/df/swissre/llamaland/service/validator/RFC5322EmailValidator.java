package com.df.swissre.llamaland.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.df.swissre.llamaland.entity.Email;

public class RFC5322EmailValidator implements EmailValidator {

    /**
     * Email regex pattern and unit-test is attributed to
     * <p>
     * https://mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
     */
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(Email email) {
        Matcher matcher = pattern.matcher(email.getEmail());
        return matcher.matches();
    }
}
