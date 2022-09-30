package org.orakzai.lab.shop.domain.business.reference.init.service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;

public interface InitializationDatabase {

    boolean isEmpty();

    void populate(String name) throws ServiceException;
    
    boolean isEmptyInitialData();
}
