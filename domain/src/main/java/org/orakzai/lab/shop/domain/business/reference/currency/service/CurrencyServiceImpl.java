package org.orakzai.lab.shop.domain.business.reference.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.reference.currency.dao.CurrencyRepository;
import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency;
//import org.orakzai.lab.shop.domain.business.reference.currency.model.Currency_;

@Service("currencyService")
public class CurrencyServiceImpl extends SalesManagerEntityServiceImpl<Long, Currency>
	implements CurrencyService {

	@Autowired
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		super(currencyRepository);
	}

	@Override
	public Currency getByCode(String code) {
		return null; //return getByField(Currency_.code, code);
	}

}
