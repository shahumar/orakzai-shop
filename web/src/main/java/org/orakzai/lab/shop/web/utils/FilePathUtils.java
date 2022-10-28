package org.orakzai.lab.shop.web.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.web.constants.Constants;

public class FilePathUtils {

	public static String buildAdminUri(@Valid MerchantStore store, HttpServletRequest req) {
		StringBuilder resourcePath = new StringBuilder();
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Map<String, String> configurations = (Map<String, String>)session.getAttribute(Constants.STORE_CONFIGURATION);
		String scheme = Constants.HTTP_SCHEME;
		if (configurations != null) {
			scheme = (String) configurations.get("scheme");
		}
		String domainName = store.getDomainName();
		if (StringUtils.isBlank(domainName)) {
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		}
		resourcePath
			.append(scheme)
			.append("://")
			.append(domainName)
			.append(req.getContextPath())
			.append(Constants.ADMIN_URI);
		
		return resourcePath.toString();
	}

	public static String buildCustomerUri(MerchantStore store, String contextPath) {
		StringBuilder path = new StringBuilder();
		String scheme = Constants.HTTP_SCHEME;
		
		String domainName = store.getDomainName();
		if (StringUtils.isBlank(domainName))
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		
		return path.append(scheme)
			.append("://")
			.append(domainName)
			.append(contextPath)
			.toString();

	}

	public static String buildStoreUri(MerchantStore store, String contextPath) {
		StringBuilder path = new StringBuilder();
		String scheme = Constants.HTTP_SCHEME;
		
		String domainName = store.getDomainName();
		if (StringUtils.isBlank(domainName))
			domainName = Constants.DEFAULT_DOMAIN_NAME;
		return path.append(scheme)
				.append("://")
				.append(domainName)
				.append(contextPath)
				.toString();
	}

}
