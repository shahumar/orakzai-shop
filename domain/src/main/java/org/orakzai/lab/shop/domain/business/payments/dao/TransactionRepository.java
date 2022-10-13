package org.orakzai.lab.shop.domain.business.payments.dao;

import java.util.List;
import java.util.Optional;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.payments.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository("transactionRepository")
public interface TransactionRepository extends SalesManagerEntityDao<Long, Transaction> {

	List<Transaction> findAllByOrder(Order order);


	
}
