package com.df.swissre.llamaland.repository;

import java.util.List;

import com.df.swissre.llamaland.entity.Citizen;
import com.df.swissre.llamaland.entity.Email;

public interface CitizenRepository {

    List<Citizen> getAll();

    List<Email> getNonUniqueEmails();

}
