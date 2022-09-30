package org.orakzai.lab.shop.domain.business.system.service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.shipping.model.ShippingConfiguration;
import org.orakzai.lab.shop.domain.business.system.dao.MerchantConfigurationRepository;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfig;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfiguration;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfigurationType;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.constants.ShippingConstants;

@Service("merchantConfigurationService")
public class MerchantConfigurationServiceImpl extends
		SalesManagerEntityServiceImpl<Long, MerchantConfiguration> implements
		MerchantConfigurationService {

	private MerchantConfigurationRepository merchantConfigurationRepository;

	@Autowired
	public MerchantConfigurationServiceImpl(
			MerchantConfigurationRepository merchantConfigurationRepository) {
			super(merchantConfigurationRepository);
			this.merchantConfigurationRepository = merchantConfigurationRepository;
	}


	@Override
	public MerchantConfiguration getMerchantConfiguration(String key, MerchantStore store) throws ServiceException {
		return merchantConfigurationRepository.findByKeyAndMerchantStore(key, store).orElse(null);
	}

	@Override
	public List<MerchantConfiguration> listByStore(MerchantStore store) throws ServiceException {
		return merchantConfigurationRepository.findAllByMerchantStore(store);
	}

	@Override
	public List<MerchantConfiguration> listByType(MerchantConfigurationType type, MerchantStore store) throws ServiceException {
		return merchantConfigurationRepository.findAllBymerchantConfigurationTypeAndMerchantStore(type, store);
	}

	@Override
	public void saveOrUpdate(MerchantConfiguration entity) throws ServiceException {



		if(entity.getId()!=null && entity.getId()>0) {
			super.update(entity);
		} else {
			super.create(entity);

		}
	}


	@Override
	public void delete(MerchantConfiguration merchantConfiguration) throws ServiceException {
		MerchantConfiguration config = merchantConfigurationRepository.findById(merchantConfiguration.getId()).get();
		if(config!=null) {
			super.delete(config);
		}
	}

	@Override
	public MerchantConfig getMerchantConfig(MerchantStore store) throws ServiceException {

		MerchantConfiguration configuration = merchantConfigurationRepository.findByKeyAndMerchantStore(Constants.MERCHANT_CONFIG, store).get();

		MerchantConfig config = null;
		if(configuration!=null) {
			String value = configuration.getValue();

			ObjectMapper mapper = new ObjectMapper();
			try {
				config = mapper.readValue(value, MerchantConfig.class);
			} catch(Exception e) {
				throw new ServiceException("Cannot parse json string " + value);
			}
		}
		return config;

	}

	@Override
	public void saveMerchantConfig(MerchantConfig config, MerchantStore store) throws ServiceException {

		MerchantConfiguration configuration = merchantConfigurationRepository.findByKeyAndMerchantStore(Constants.MERCHANT_CONFIG, store).get();

		if(configuration==null) {
			configuration = new MerchantConfiguration();
			configuration.setMerchantStore(store);
			configuration.setKey(Constants.MERCHANT_CONFIG);
		}

		String value = config.toJSONString();
		configuration.setValue(value);
		if(configuration.getId()!=null && configuration.getId()>0) {
			super.update(configuration);
		} else {
			super.create(configuration);

		}

	}



}
