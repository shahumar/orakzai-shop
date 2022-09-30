package org.orakzai.lab.shop.domain.business.catalog.product.service.attribute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.attribute.ProductAttributeRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.attribute.ProductAttribute;
import org.orakzai.lab.shop.domain.business.catalog.product.model.price.ProductPrice;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;


@Service("productAttributeService")
public class ProductAttributeServiceImpl extends
		SalesManagerEntityServiceImpl<Long, ProductAttribute> implements ProductAttributeService {

	private ProductAttributeRepository productAttributeRepository;

	@Autowired
	public ProductAttributeServiceImpl(ProductAttributeRepository productAttributeRepository) {
		super(productAttributeRepository);
		this.productAttributeRepository = productAttributeRepository;
	}

	@Override
	public ProductAttribute getById(Long id) {

		return productAttributeRepository.findById(id).get();

	}


	@Override
	public List<ProductAttribute> getByOptionId(MerchantStore store,
			Long id) throws ServiceException {

		return productAttributeRepository.getByOptionId(store, id);

	}

	@Override
	public List<ProductAttribute> getByAttributeIds(MerchantStore store,
			List<Long> ids) throws ServiceException {

		return productAttributeRepository.getByAttributeIds(store, ids);

	}

	@Override
	public List<ProductAttribute> getByOptionValueId(MerchantStore store,
			Long id) throws ServiceException {

		return productAttributeRepository.getByOptionValueId(store, id);

	}

	/**
	 * Returns all product attributes
	 */
	@Override
	public List<ProductAttribute> getByProductId(MerchantStore store,
			Product product, Language language) throws ServiceException {
		return productAttributeRepository.getByProduct(store, product, language);

	}


	@Override
	public void saveOrUpdate(ProductAttribute productAttribute)
			throws ServiceException {
		productAttributeRepository.save(productAttribute);
	}

	@Override
	public void delete(ProductAttribute attribute) throws ServiceException {

		//override method, this allows the error that we try to remove a detached instance
		attribute = this.getById(attribute.getId());
		super.delete(attribute);

	}

}
