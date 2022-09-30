package org.orakzai.lab.shop.domain.business.catalog.product.service.manufacturer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.manufacturer.ManufacturerRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.description.ProductDescription;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.Manufacturer;
import org.orakzai.lab.shop.domain.business.catalog.product.model.manufacturer.ManufacturerDescription;
import org.orakzai.lab.shop.domain.business.customer.model.Customer;
//import org.orakzai.lab.shop.domain.business.customer.service.CustomerServiceImpl;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;
//import org.orakzai.lab.shop.domain.business.search.service.SearchService;



@Service("manufacturerService")
public class ManufacturerServiceImpl extends
		SalesManagerEntityServiceImpl<Long, Manufacturer> implements ManufacturerService {

//	@Autowired
//	SearchService searchService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ManufacturerServiceImpl.class);
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	public ManufacturerServiceImpl(
		ManufacturerRepository manufacturerRepository) {
		super(manufacturerRepository);
		this.manufacturerRepository = manufacturerRepository;
	}

	@Override
	public void delete(Manufacturer manufacturer) throws ServiceException{
		manufacturer =  this.getById(manufacturer.getId() );
		super.delete( manufacturer );
	}

	@Override
	public int getCountManufAttachedProducts( Manufacturer manufacturer ) throws ServiceException {
//		return manufacturerDao.getCountManufAttachedProducts( manufacturer );
		return 0;
	}


	@Override
	public List<Manufacturer> listByStore(MerchantStore store, Language language) throws ServiceException {
		return manufacturerRepository.findAllByStoreAndLanguage(store, language);
	}

	@Override
	public List<Manufacturer> listByStore(MerchantStore store) throws ServiceException {
//		return manufacturerDao.listByStore(store);
		return null;
	}

	@Override
	public List<Manufacturer> listByProductsByCategoriesId(MerchantStore store, List<Long> ids, Language language) throws ServiceException {
//		return manufacturerDao.listByProductsByCategoriesId(store, ids, language);
		return null;
	}

	@Override
	public void addManufacturerDescription(Manufacturer manufacturer, ManufacturerDescription description)
			throws ServiceException {


		if(manufacturer.getDescriptions()==null) {
			manufacturer.setDescriptions(new HashSet<ManufacturerDescription>());
		}

		manufacturer.getDescriptions().add(description);
		description.setManufacturer(manufacturer);
		update(manufacturer);
	}

	@Override
	public void saveOrUpdate(Manufacturer manufacturer) throws ServiceException {

		LOGGER.debug("Creating Manufacturer");

		if(manufacturer.getId()!=null && manufacturer.getId()>0) {
		   super.update(manufacturer);

		} else {
		   super.create(manufacturer);

		}
	}
}
