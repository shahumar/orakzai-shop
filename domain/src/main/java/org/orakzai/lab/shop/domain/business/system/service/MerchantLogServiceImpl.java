package org.orakzai.lab.shop.domain.business.system.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.system.dao.MerchantLogRepository;
import org.orakzai.lab.shop.domain.business.system.model.MerchantLog;

@Service("merchantLogService")
public class MerchantLogServiceImpl extends
		SalesManagerEntityServiceImpl<Long, MerchantLog> implements
		MerchantLogService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantLogServiceImpl.class);

	private MerchantLogRepository merchantLogRepository;

	@Autowired
	public MerchantLogServiceImpl(
			MerchantLogRepository merchantLogDao) {
			super(merchantLogDao);
			this.merchantLogRepository = merchantLogDao;
	}


}
