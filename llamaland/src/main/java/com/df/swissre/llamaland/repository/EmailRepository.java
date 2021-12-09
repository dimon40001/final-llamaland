package com.df.swissre.llamaland.repository;

import java.util.List;

import com.df.swissre.llamaland.entity.Email;

public interface EmailRepository {

    List<Email> getAll();
}
