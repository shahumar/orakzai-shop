package org.orakzai.lab.shop.domain.business.generic.service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;

import java.io.Serializable;
import java.util.List;

public interface SalesManagerEntityService<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>> extends TransactionalAspectAwareService {
    void save(E entity) throws ServiceException;

    void update(E entity) throws ServiceException;


    void create(E entity) throws ServiceException;


    void delete(E entity) throws ServiceException;

    E refresh(E entity);


    E getById(K id);

    List<E> list();

    E getEntity(Class<? extends E> clazz, K id);

    Long count();

    void flush();

    void clear();
}
