package org.orakzai.lab.shop.domain.business.generic.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Repository("entityDao")
@Transactional
public class SalesManagerJpaDaoSupportImpl implements SalesManagerJpaDaoSupport {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> TypedQuery<T> buildTypedQuery(CriteriaQuery<T> criteria, Integer limit, Integer offset) {
        TypedQuery<T> query = getEntityManager().createQuery(criteria);
        if (offset != null) {
            query.setFirstResult(offset);
        }
        if (limit != null) {
            query.setMaxResults(limit);
        }
        return query;
    }

    @Override
    public void filterCriteriaQuery(CriteriaQuery<?> criteria, Expression<Boolean> filter) {
        if (filter != null) {
            criteria.where(filter);
        }
    }

    @Override
    public <T> Root<T> rootCriteriaQuery(CriteriaBuilder builder, CriteriaQuery<?> criteria, Class<T> objectClass) {
        return criteria.from(objectClass);
    }

    @Override
    public <T, K> T getEntity(Class<T> clazz, K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public <T, V> T getByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.where(builder.equal(root.get(attribute), fieldValue));

        try {
            return buildTypedQuery(criteria, null, null).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public <T> void update(T entity) {
        if (!getEntityManager().contains(entity)) {
            getEntityManager().merge(entity);
            //throw new PersistenceException("Updated entity must be attached");
        }
    }

    @Override
    public <T> T refresh(T entity) {
        getEntityManager().refresh(entity);
//
        return entity;
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }

    @Override
    public void clear() {
        getEntityManager().clear();
    }

    @Override
    public <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter, Integer limit, Integer offset, Order... orders) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(objectClass);
        rootCriteriaQuery(builder, criteria, objectClass);

        if (filter != null) {
            filterCriteriaQuery(criteria, filter);
        }
        if (orders != null && orders.length > 0) {
            criteria.orderBy(orders);
        }
        TypedQuery<T> query = buildTypedQuery(criteria, limit, offset);

        List<T> entities = query.getResultList();

        if (orders == null || orders.length == 0) {
            sort(entities);
        }

        return entities;
    }

    @Override
    public <T> List<T> listEntity(Class<T> objectClass) {
        return listEntity(objectClass, null, null, null);
    }

    @Override
    public <T> List<T> listEntity(Class<T> objectClass, Expression<Boolean> filter) {
        return listEntity(objectClass, filter, null, null);
    }

    @Override
    public <T, V> List<T> listEntityByField(Class<T> objectClass, SingularAttribute<? super T, V> attribute, V fieldValue) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(objectClass);

        Root<T> root = rootCriteriaQuery(builder, criteria, objectClass);
        criteria.where(builder.equal(root.get(attribute), fieldValue));

        List<T> entities = buildTypedQuery(criteria, null, null).getResultList();

        sort(entities);

        return entities;
    }

    @Override
    public <T> Long countEntity(Class<T> clazz) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<T> root = rootCriteriaQuery(builder, criteria, clazz);

        criteria.select(builder.count(root));

        return buildTypedQuery(criteria, null, null).getSingleResult();
    }

    @Override
    public <T, V> Long countEntityByField(Class<T> clazz, SingularAttribute<? super T, V> attribute, V fieldValue) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        Root<T> root = rootCriteriaQuery(builder, criteria, clazz);
        criteria.select(builder.count(root));

        Expression<Boolean> filter = builder.equal(root.get(attribute), fieldValue);
        filterCriteriaQuery(criteria, filter);

        return buildTypedQuery(criteria, null, null).getSingleResult();
    }

    @Override
    public <T> Long countEntity(Class<T> clazz, Expression<Boolean> filter) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        Root<T> root = rootCriteriaQuery(builder, criteria, clazz);
        criteria.select(builder.count(root));

        filterCriteriaQuery(criteria, filter);

        return buildTypedQuery(criteria, null, null).getSingleResult();
    }

    @Override
    public <E> E getSingleResultOrNull(CriteriaQuery<E> cq) {
        try {
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public <E> E getSingleResultOrNull(TypedQuery<E> tq) {
        try {
            return tq.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public <T> void sort(List<T> entities) {
        Object[] a = entities.toArray();
        Arrays.sort(a);
        ListIterator<T> i = entities.listIterator();
        for (int j = 0; j < a.length; j++) {
            i.next();
            i.set((T) a[j]);
        }
    }


}
