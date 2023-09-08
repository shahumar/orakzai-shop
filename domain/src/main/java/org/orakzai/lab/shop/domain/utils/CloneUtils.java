package org.orakzai.lab.shop.domain.utils;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class CloneUtils {
    private CloneUtils(){}

    public static LocalDate clone(LocalDate date) {
        if (date != null) {
        	Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        	LocalDate newDate = LocalDate.ofInstant(instant, ZoneId.systemDefault());
        	return newDate;
        }
        return null;
    }
}
