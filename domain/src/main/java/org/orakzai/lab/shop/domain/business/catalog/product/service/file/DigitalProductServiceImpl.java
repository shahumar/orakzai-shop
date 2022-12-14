package org.orakzai.lab.shop.domain.business.catalog.product.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.file.DigitalProductRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.DigitalProduct;
import org.orakzai.lab.shop.domain.business.catalog.product.service.ProductService;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.InputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.modules.cms.content.StaticContentFileManager;

@Service("digitalProductService")
public class DigitalProductServiceImpl extends SalesManagerEntityServiceImpl<Long, DigitalProduct>
	implements DigitalProductService {


	private DigitalProductRepository digitalProductRepository;

//    @Autowired
//    StaticContentFileManager productDownloadsFileManager;

    @Autowired
    ProductService productService;

	@Autowired
	public DigitalProductServiceImpl(DigitalProductRepository digitalProductRepository) {
		super(digitalProductRepository);
		this.digitalProductRepository = digitalProductRepository;
	}

	@Override
	public void addProductFile(Product product, DigitalProduct digitalProduct, InputContentFile inputFile) throws ServiceException {

		Assert.notNull(digitalProduct,"DigitalProduct cannot be null");
		Assert.notNull(product,"Product cannot be null");
		digitalProduct.setProduct(product);

		try {

			Assert.notNull(inputFile.getFile(),"InputContentFile.file cannot be null");

			Assert.notNull(product.getMerchantStore(),"Product.merchantStore cannot be null");
			this.saveOrUpdate(digitalProduct);

//			productDownloadsFileManager.addFile(product.getMerchantStore().getCode(), inputFile);

			product.setProductVirtual(true);
			productService.update(product);

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			try {

				if(inputFile.getFile()!=null) {
					inputFile.getFile().close();
				}

			} catch(Exception ignore) {}
		}


	}

	@Override
	public DigitalProduct getByProduct(MerchantStore store, Product product) throws ServiceException {
		var digtitalProduct = digitalProductRepository.findByStoreAndProduct(store, product);
		if (digtitalProduct.isPresent()) {
			return digtitalProduct.get();
		}
		return null;
	}

	@Override
	public void delete(DigitalProduct digitalProduct) throws ServiceException {

		Assert.notNull(digitalProduct,"DigitalProduct cannot be null");
		Assert.notNull(digitalProduct.getProduct(),"DigitalProduct.product cannot be null");
		//refresh file
		digitalProduct = this.getById(digitalProduct.getId());
		super.delete(digitalProduct);
//		productDownloadsFileManager.removeFile(digitalProduct.getProduct().getMerchantStore().getCode(), FileContentType.PRODUCT, digitalProduct.getProductFileName());
		digitalProduct.getProduct().setProductVirtual(false);
		productService.update(digitalProduct.getProduct());
	}


	@Override
	public void saveOrUpdate(DigitalProduct digitalProduct) throws ServiceException {

		Assert.notNull(digitalProduct,"DigitalProduct cannot be null");
		Assert.notNull(digitalProduct.getProduct(),"DigitalProduct.product cannot be null");
		if(digitalProduct.getId()==null || digitalProduct.getId().longValue()==0) {
			super.save(digitalProduct);
		} else {
			super.create(digitalProduct);
		}

		digitalProduct.getProduct().setProductVirtual(true);
		productService.update(digitalProduct.getProduct());


	}




}
