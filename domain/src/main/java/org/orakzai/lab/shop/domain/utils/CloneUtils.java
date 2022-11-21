package org.orakzai.lab.shop.domain.utils;

import java.time.LocalDate;

public class CloneUtils {
    private CloneUtils(){}

    public static LocalDate clone(LocalDate date) {
        if (date != null) {
        	try {
        		return LocalDate.from(date);
        	} catch(Date)
        }
        return null;
    }
}
