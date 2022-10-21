package org.orakzai.lab.shop.domain.business.customer.service.attribute;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.customer.dao.attribute.CustomerOptionSetRepository;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

@Service("customerOptionSetService")
public class CustomerOptionSetServiceImpl extends
		SalesManagerEntityServiceImpl<Long, CustomerOptionSet> implements CustomerOptionSetService {


	@Autowired
	private CustomerOptionSetRepository customerOptionSetRepository;


	@Autowired
	public CustomerOptionSetServiceImpl(
			CustomerOptionSetRepository customerOptionSetDao) {
			super(customerOptionSetDao);
			this.customerOptionSetRepository = customerOptionSetDao;
	}


	@Override
	public List<CustomerOptionSet> listByOption(CustomerOption option, MerchantStore store) throws ServiceException {
		Validate.notNull(store,"merchant store cannot be null");
		Validate.notNull(option,"option cannot be null");
		return null;
//		return customerOptionSetRepository.getByOptionId(store, option.getId());
	}

	@Override
	public List<CustomerOptionSet> listByStore(MerchantStore store, Language language) throws ServiceException {
		Validate.notNull(store,"merchant store cannot be null");
		return null;
//		return customerOptionSetRepository.listByStore(store,language);
	}


	@Override
	public void saveOrUpdate(CustomerOptionSet entity) throws ServiceException {
		Validate.notNull(entity,"customer option set cannot be null");

		if(entity.getId()>0) {
			super.update(entity);
		} else {
			super.create(entity);
		}

	}


	@Override
	public List<CustomerOptionSet> listByOptionValue(
			CustomerOptionValue optionValue, MerchantStore store)
			throws ServiceException {
		return null;
//		return customerOptionSetRepository.getByOptionValueId(store, optionValue.getId());
	}

}
