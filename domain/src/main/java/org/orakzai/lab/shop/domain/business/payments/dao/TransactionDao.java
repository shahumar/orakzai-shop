package org.orakzai.lab.shop.domain.business.payments.dao;

import java.util.List;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository("transactionDao")
public interface TransactionDao extends SalesManagerEntityDao<Long, Transaction> {

//	List<Transaction> listByOrder(Order order);


	
}
