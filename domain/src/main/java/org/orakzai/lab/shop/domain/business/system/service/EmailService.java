package org.orakzai.lab.shop.domain.business.system.service;

import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.modules.email.Email;
import org.orakzai.lab.shop.domain.modules.email.EmailConfig;


public interface EmailService {

	public void sendHtmlEmail(MerchantStore store, Email email) throws ServiceException, Exception;

	public EmailConfig getEmailConfiguration(MerchantStore store) throws ServiceException;

	public void saveEmailConfiguration(EmailConfig emailConfig, MerchantStore store) throws ServiceException;

}
