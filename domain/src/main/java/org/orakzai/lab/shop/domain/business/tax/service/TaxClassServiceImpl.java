package org.orakzai.lab.shop.domain.business.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.tax.dao.taxclass.TaxClassRepository;
import org.orakzai.lab.shop.domain.business.tax.model.taxclass.TaxClass;

@Service("taxClassService")
public class TaxClassServiceImpl extends SalesManagerEntityServiceImpl<Long, TaxClass>
		implements TaxClassService {

	private TaxClassRepository taxClassRepository;

	@Autowired
	public TaxClassServiceImpl(TaxClassRepository taxClassRepository) {
		super(taxClassRepository);

		this.taxClassRepository = taxClassRepository;
	}

	@Override
	public List<TaxClass> listByStore(MerchantStore store) throws ServiceException {
		return taxClassRepository.findAllByMerchantStore(store);
	}

	@Override
	public TaxClass getByCode(String code) throws ServiceException {
		return taxClassRepository
				.findByCode(code)
				.orElseThrow(() -> new ServiceException("Not found"));
	}

	@Override
	public TaxClass getByCode(String code, MerchantStore store) throws ServiceException {
		return taxClassRepository
				.findByCodeAndMerchantStore(code, store)
				.orElseThrow(() -> new ServiceException("Not found"));
	}

	@Override
	public TaxClass getById(Long id){
		return taxClassRepository
				.findById(id)
				.orElse(null);
	}


}
