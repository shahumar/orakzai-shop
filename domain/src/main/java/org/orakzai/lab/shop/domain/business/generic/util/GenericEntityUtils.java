package org.orakzai.lab.shop.domain.business.generic.util;

import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class GenericEntityUtils {

    public static Class<?> getGenericEntityClassFromComponentDefinition(Class<?> clazz) {
        int retriesCount = 0;

        while(true) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] argumentTypes = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();

                for (Type argumentType : argumentTypes) {
                    Class<?> argumentClass;

                    if (argumentType instanceof ParameterizedType) {
                        argumentClass = (Class<?>) ((ParameterizedType) argumentType).getRawType();
                    } else {
                        argumentClass = (Class<?>) argumentType;
                    }

                    if (SalesManagerEntity.class.isAssignableFrom(argumentClass)) {
                        return argumentClass;
                    }
                }
            }

            clazz = clazz.getSuperclass();
            retriesCount ++;

            if (retriesCount > 5) {
                throw new IllegalArgumentException("Unable to find a generic type extending GenericEntity.");
            }
        }
    }

    private GenericEntityUtils() {
    }
}
