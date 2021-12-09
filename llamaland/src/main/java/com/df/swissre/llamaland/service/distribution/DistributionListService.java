package com.df.swissre.llamaland.service.distribution;

import java.time.LocalDate;
import java.util.List;

import com.df.swissre.llamaland.entity.Citizen;

public interface DistributionListService {

    List<Citizen> getDistributionListForDate(LocalDate date);

}
