package org.orakzai.lab.shop.domain.business.system.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.system.model.MerchantConfiguration;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.domain.modules.email.EmailConfig;
import org.orakzai.lab.shop.domain.modules.email.HtmlEmailSender;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private MerchantConfigurationService merchantConfigurationService;

	@Autowired
	private HtmlEmailSender sender;

	@Override
	public void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception {

		EmailConfig emailConfig = getEmailConfiguration(store);
		sender.setEmailConfig(emailConfig);
		sender.send(email);
	}

	@Override
	public EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException {

		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.EMAIL_CONFIG, store);
		EmailConfig emailConfig = null;
		if(configuration!=null) {
			String value = configuration.getValue();

			ObjectMapper mapper = new ObjectMapper();
			try {
				emailConfig = mapper.readValue(value, EmailConfig.class);
			} catch(Exception e) {
				throw new ServiceException("Cannot parse json string " + value);
			}
		} 
		return emailConfig;
	}


	@Override
	public void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException {
		MerchantConfiguration configuration = merchantConfigurationService.getMerchantConfiguration(Constants.EMAIL_CONFIG, store);
		if(configuration==null) {
			configuration = new MerchantConfiguration();
			configuration.setMerchantStore(store);
			configuration.setKey(Constants.EMAIL_CONFIG);
		}

		String value = emailConfig.toJSONString();
		configuration.setValue(value);
		merchantConfigurationService.saveOrUpdate(configuration);
	}

}
