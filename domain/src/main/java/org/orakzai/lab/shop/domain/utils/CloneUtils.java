package org.orakzai.lab.shop.domain.utils;

import java.time.LocalDate;

public class CloneUtils {
    private CloneUtils(){}

    public static LocalDate clone(LocalDate date) {
        if (date != null) {
            return LocalDate.from(date);
        }
        return null;
    }
}
