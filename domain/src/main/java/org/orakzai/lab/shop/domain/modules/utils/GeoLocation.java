package org.orakzai.lab.shop.domain.modules.utils;

import org.orakzai.lab.shop.domain.business.common.model.Address;

public interface GeoLocation {
	
	Address getAddress(String ipAddress) throws Exception;

}
