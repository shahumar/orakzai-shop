package org.orakzai.lab.shop.domain.business.generic.dao;

import org.orakzai.lab.shop.domain.business.generic.model.SalesManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;


@NoRepositoryBean
public interface SalesManagerEntityDao<K extends Serializable & Comparable<K>, E extends SalesManagerEntity<K, ?>> extends JpaRepository<E, K>{    
    
}
