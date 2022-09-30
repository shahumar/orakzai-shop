package org.orakzai.lab.shop.domain.business.reference.currency.service;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;

public interface CurrencyService extends SalesManagerEntityService<Long, Currency> {

	Currency getByCode(String code);

}
