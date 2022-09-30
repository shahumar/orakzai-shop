package org.orakzai.lab.shop.domain.business.catalog.product.service.image;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import org.orakzai.lab.shop.domain.business.catalog.product.dao.image.ProductImageRepository;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.ProductImageSize;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImageDescription;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.ImageContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.generic.service.SalesManagerEntityServiceImpl;
import org.orakzai.lab.shop.domain.modules.cms.product.ProductFileManager;

@Service("productImage")
public class ProductImageServiceImpl extends SalesManagerEntityServiceImpl<Long, ProductImage>
	implements ProductImageService {

	private ProductImageRepository productImageRepository;

	@Autowired
	public ProductImageServiceImpl(ProductImageRepository productImageRepository) {
		super(productImageRepository);
		this.productImageRepository = productImageRepository;
	}

	@Autowired
	private ProductFileManager productFileManager;

	public ProductImage getById(Long id) {
//		return productImageRepository.getProductImageById(id);
		return null;
	}


	@Override
	public void addProductImages(Product product, List<ProductImage> productImages) throws ServiceException {
		try {
			for(ProductImage productImage : productImages) {
				Assert.notNull(productImage.getImage(), "Image should not be null");
		        InputStream inputStream = productImage.getImage();
		        ImageContentFile cmsContentImage = new ImageContentFile();
		        cmsContentImage.setFileName( productImage.getProductImage() );
		        cmsContentImage.setFile( inputStream );
		        cmsContentImage.setFileContentType(FileContentType.PRODUCT);

				addProductImage(product,productImage,cmsContentImage);
			}

		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public void addProductImage(Product product, ProductImage productImage, ImageContentFile inputImage) throws ServiceException {

		productImage.setProduct(product);
		try {
			Assert.notNull(inputImage.getFile(),"ImageContentFile.file cannot be null");
			productFileManager.addProductImage(productImage, inputImage);
			this.saveOrUpdate(productImage);

		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			try {
				if(inputImage.getFile()!=null) {
					inputImage.getFile().close();
				}
			} catch(Exception ignore) {

			}
		}
	}

	@Override
	public void saveOrUpdate(ProductImage productImage) throws ServiceException {

		//save or update (persist and attach entities
		if(productImage.getId()!=null && productImage.getId()>0) {
			super.update(productImage);
		} else {
			List<ProductImageDescription> descriptions = productImage.getDescriptions();
			productImage.setDescriptions(null);
			super.save(productImage);
			if(descriptions!=null && descriptions.size()>0) {
				for(ProductImageDescription description : descriptions) {
					this.addProductImageDescription(productImage, description);
				}
			}
		}
	}

	public void addProductImageDescription(ProductImage productImage, ProductImageDescription description)
	throws ServiceException {


			if(productImage.getDescriptions()==null) {
				productImage.setDescriptions(new ArrayList<ProductImageDescription>());
			}

			productImage.getDescriptions().add(description);
			description.setProductImage(productImage);
			update(productImage);


	}

	//TODO get default product image


	@Override
	public OutputContentFile getProductImage(ProductImage productImage, ProductImageSize size) throws ServiceException {


		ProductImage pi = new ProductImage();
		String imageName = productImage.getProductImage();
		if(size == ProductImageSize.LARGE) {
			imageName = "L-" + imageName;
		}

		if(size == ProductImageSize.SMALL) {
			imageName = "S-" + imageName;
		}

		pi.setProductImage(imageName);
		pi.setProduct(productImage.getProduct());

//		OutputContentFile outputImage = productFileManager.getProductImage(pi);

//		return outputImage;
		return null;

	}

	@Override
	public OutputContentFile getProductImage(final String storeCode, final String productCode, final String fileName, final ProductImageSize size) throws ServiceException {
		OutputContentFile outputImage = productFileManager.getProductImage(storeCode, productCode, fileName, size);
		return outputImage;

	}

	@Override
	public List<OutputContentFile> getProductImages(Product product) throws ServiceException {
		return productFileManager.getImages(product);
	}

	@Override
	public void removeProductImage(ProductImage productImage) throws ServiceException {

//		productFileManager.removeProductImage(productImage);
//
//		ProductImage p = this.getById(productImage.getId());
//
//
//		this.delete(p);

	}
	
	@Override
	public void deleteProductImages(Product product) throws ServiceException {
		productImageRepository.deleteAllByProduct(product);
	}
}
