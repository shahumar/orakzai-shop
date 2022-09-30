package org.orakzai.lab.shop.domain.business.generic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;

import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;

import java.io.Serializable;
import java.util.List;


public interface SalesManagerJpaDaoSupport {

    <T> TypedQuery<T> buildTypedQuery(CriteriaQuery<T> criteria, Integer limit, Integer offset);

    void filterCriteriaQuery(CriteriaQuery<?> criteria, Expression<Boolean> filter);

    <T> Root<T> rootCriteriaQuery(CriteriaBuilder builder, CriteriaQuery<?> criteria, Class<T> objectClass);

    public <T, K> T getEntity(Class<T> clazz, K id);

    public <T, V> T getByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue);

    <T> void update(T entity);

    <T> T refresh(T entity);

    public void flush();

    public void clear();

    <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter, Integer limit, Integer offset, Order... orders);

    <T> List<T> listEntity(Class<T> objectClass);

    <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter);

    <T, V> List<T> listEntityByField(Class<T> objectClass, SingularAttribute<? super T, V> attribute, V fieldValue) ;

    <T> Long countEntity(Class<T> clazz);
    <T, V> Long countEntityByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue);

    <T> Long countEntity(Class<T> clazz, Expression<Boolean> filter);

    <E> E getSingleResultOrNull(CriteriaQuery<E> cq);

    <E> E getSingleResultOrNull(TypedQuery<E> tq);

    EntityManager getEntityManager();

    <T> void sort(List<T> entities);

}
