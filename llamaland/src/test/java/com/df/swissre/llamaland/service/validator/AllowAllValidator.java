package com.df.swissre.llamaland.service.validator;

import com.df.swissre.llamaland.entity.Email;

public class AllowAllValidator implements EmailValidator{
    @Override
    public boolean isValid(Email email) {
        return true;
    }
}
