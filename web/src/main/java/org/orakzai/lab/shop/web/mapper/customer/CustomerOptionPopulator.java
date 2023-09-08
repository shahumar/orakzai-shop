package org.orakzai.lab.shop.web.mapper.customer;

import java.util.ArrayList;
import java.util.List;

import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOption;
import org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionSet;
import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
import org.orakzai.lab.shop.domain.utils.AbstractDataPopulator;
import org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOptionValue;

public class CustomerOptionPopulator extends AbstractDataPopulator<CustomerOption, org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption>{

	private CustomerOptionSet optionSet;
	
	public CustomerOptionSet getOptionSet() {
		return optionSet;
	}
	
	public void setOptionSet(CustomerOptionSet optionSet) {
		this.optionSet = optionSet;
	}

	@Override
	public org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption populate(CustomerOption source,
			org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption target, MerchantStore store,
			Language language) throws ConversionException {
		org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption customerOption = target;
		if (customerOption == null) {
			customerOption = new org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption();
		}
		customerOption.setId(source.getId());
		customerOption.setType(source.getCustomerOptionType());
		customerOption.setName(source.getDescriptionsSettoList().get(0).getName());
		
		List<CustomerOptionValue> values = customerOption.getAvailableValues();
		if (values == null) {
			values = new ArrayList<CustomerOptionValue>();
			customerOption.setAvailableValues(values);
		}
		
		org.orakzai.lab.shop.domain.business.customer.model.attribute.CustomerOptionValue optionValue = optionSet.getCustomerOptionValue();
		CustomerOptionValue custOptValue = new CustomerOptionValue();
		custOptValue.setId(optionValue.getId());
		custOptValue.setLanguage(language.getCode());
		custOptValue.setName(optionValue.getDescriptionsSettoList().get(0).getName());
		values.add(custOptValue);
		return customerOption;
		
	}

	@Override
	protected org.orakzai.lab.shop.web.admin.entity.customer.attribute.CustomerOption createTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
