package org.orakzai.lab.shop.domain.business.generic.service;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.orakzai.lab.shop.domain.business.generic.util.GenericEntityUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;

public class SalesManagerEntityServiceImpl<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>>
        implements SalesManagerEntityService<K, E>{

    private Class<E> objectClass;

    private SalesManagerEntityDao<K, E> genericDao;

    @SuppressWarnings("unchecked")
    public SalesManagerEntityServiceImpl(SalesManagerEntityDao<K, E> genericDao) {
        this.genericDao = genericDao;
        this.objectClass = (Class<E>) GenericEntityUtils.getGenericEntityClassFromComponentDefinition(getClass());
    }

    protected final Class<E> getObjectClass() {
        return objectClass;
    }


    public E getEntity(Class<? extends E> clazz, K id) {
        return genericDao.findById(id).get();        
    }


    public E getById(K id) {
        return genericDao.findById(id).get();
    }

    protected <V> E getByField(SingularAttribute<? super E, V> attrs, V fieldValue) {
//        return genericDao.getByField(getObjectClass(), attrs, fieldValue);
    	return null;
    }


    public void save(E entity) throws ServiceException {
        genericDao.save(entity);
    }


    public void create(E entity) throws ServiceException {
        createEntity(entity);
    }


    protected void createEntity(E entity) throws ServiceException {
        save(entity);
    }


	public void update(E entity) throws ServiceException {
		updateEntity(entity);
    }

    protected void updateEntity(E entity) throws ServiceException {
        genericDao.save(entity);
    }


    public void delete(E entity) throws ServiceException {
        genericDao.delete(entity);
    }


    public void flush() {
        genericDao.flush();
    }


    public void clear() {
//        genericDao.clear();
    }


    public E refresh(E entity) {
    	return null;
//        return genericDao.refresh(entity);
    }


    public List<E> list() {
        return genericDao.findAll();
    }

    protected <V> List<E> listByField(SingularAttribute<? super E, V> attribute, V fieldValue) {
//        return genericDao.listEntityByField(getObjectClass(), attribute, fieldValue);
    	return null;
    }


    public Long count() {
        return genericDao.count();
    }
}
