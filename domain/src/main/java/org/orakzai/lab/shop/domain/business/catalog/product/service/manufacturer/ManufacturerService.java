package org.orakzai.lab.shop.domain.business.catalog.product.service.manufacturer;

import java.util.List;

import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityService;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

public interface ManufacturerService extends SalesManagerEntityService<Long, Manufacturer> {

	List<Manufacturer> listByStore(MerchantStore store, Language language)
			throws ServiceException;

	List<Manufacturer> listByStore(MerchantStore store) throws ServiceException;

	void saveOrUpdate(Manufacturer manufacturer) throws ServiceException;

	void addManufacturerDescription(Manufacturer manufacturer, ManufacturerDescription description) throws ServiceException;

	int getCountManufAttachedProducts( Manufacturer manufacturer )  throws ServiceException;


	/**
	 * List manufacturers by products from a given list of categories
	 * @param store
	 * @param ids
	 * @param language
	 * @return
	 * @throws ServiceException
	 */
	List<Manufacturer> listByProductsByCategoriesId(MerchantStore store,
			List<Long> ids, Language language) throws ServiceException;

}
