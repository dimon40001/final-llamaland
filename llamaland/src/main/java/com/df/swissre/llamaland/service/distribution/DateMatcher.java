package com.df.swissre.llamaland.service.distribution;

import java.time.LocalDate;

public interface DateMatcher {

    boolean matches(LocalDate dateInPast, LocalDate dateInFuture);

}
