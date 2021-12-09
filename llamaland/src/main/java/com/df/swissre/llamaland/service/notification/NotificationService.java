package com.df.swissre.llamaland.service.notification;

import java.util.List;

public interface NotificationService<T> {

    int notify(List<T> recipients);

}
