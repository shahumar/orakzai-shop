package org.orakzai.lab.shop.domain.modules.order;

import java.io.ByteArrayOutputStream;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.order.model.Order;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface InvoiceModule {
	
	public ByteArrayOutputStream createInvoice(MerchantStore store, Order order, Language language) throws Exception;

}
