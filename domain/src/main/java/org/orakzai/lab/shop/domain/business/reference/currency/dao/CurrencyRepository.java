package org.orakzai.lab.shop.domain.business.reference.currency.dao;

import org.orakzai.lab.shop.domain.business.generic.dao.SalesManagerEntityDao;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
import org.springframework.stereotype.Repository;

@Repository("currencyDao")
public interface CurrencyRepository extends SalesManagerEntityDao<Long, Currency> {

}
