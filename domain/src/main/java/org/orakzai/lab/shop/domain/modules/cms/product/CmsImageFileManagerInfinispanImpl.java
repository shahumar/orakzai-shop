package org.orakzai.lab.shop.domain.modules.cms.product;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.infinispan.manager.EmbeddedCacheManager;
import org.orakzai.lab.shop.domain.business.catalog.product.model.Product;
import org.orakzai.lab.shop.domain.business.catalog.product.model.file.ProductImageSize;
import org.orakzai.lab.shop.domain.business.catalog.product.model.image.ProductImage;
import org.orakzai.lab.shop.domain.business.content.model.FileContentType;
import org.orakzai.lab.shop.domain.business.content.model.ImageContentFile;
import org.orakzai.lab.shop.domain.business.content.model.OutputContentFile;
import org.orakzai.lab.shop.domain.business.generic.exception.ServiceException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component("cmsProductImage")
public class CmsImageFileManagerInfinispanImpl
    implements ProductImagePut, ProductImageGet, ProductImageRemove
{

    private static final Logger LOGGER = LoggerFactory.getLogger( CmsImageFileManagerInfinispanImpl.class );

    private static CmsImageFileManagerInfinispanImpl fileManager = null;
    
    private final static String ROOT_NAME = "product-merchant";
    
    private final static String SMALL = "SMALL";
    private final static String LARGE = "LARGE";
    
    private String rootName = ROOT_NAME;
    
    @Autowired
    EmbeddedCacheManager cacheManager;

    @Override
    public void addProductImage( ProductImage productImage,
    		ImageContentFile contentImage )
        throws ServiceException
    {

        try
        {

            // node
        	StringBuilder nodePath = new StringBuilder();
        	nodePath
        		.append(productImage
        				.getProduct()
        				.getMerchantStore()
        				.getCode())
        		.append(Constants.SLASH)
        		.append(productImage.getProduct().getSku())
        		.append(Constants.SLASH);


        	if(contentImage.getFileContentType().name().equals(FileContentType.PRODUCT.name())) {
        		nodePath.append(SMALL);
        	} else if(contentImage.getFileContentType().name().equals(FileContentType.PRODUCTLG.name())) {
        		nodePath.append(LARGE);
        	}

        	String productNode = nodePath.toString();	
        	log.info("ImagePath" + productNode);
            InputStream isFile = contentImage.getFile();

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy( isFile, output );
            
            cacheManager.getCache("default").put(productNode, output.toByteArray());


        }
        catch ( Exception e )
        {

            throw new ServiceException( e );

        }

    }

    @Override
    public OutputContentFile getProductImage( ProductImage productImage )
        throws ServiceException
    {

       return getProductImage(productImage.getProduct().getMerchantStore().getCode(),productImage.getProduct().getSku(),productImage.getProductImage());

    }


    public List<OutputContentFile> getImages( MerchantStore store, FileContentType imageContentType )
        throws ServiceException
    {

         return getImages(store.getCode(),imageContentType);

    }

    @Override
    public List<OutputContentFile> getImages( Product product )
        throws ServiceException
    {

//        if ( cacheManager.getTreeCache() == null )
//        {
//            throw new ServiceException( "CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()" );
//        }
//
//        List<OutputContentFile> images = new ArrayList<OutputContentFile>();
//
//
//        try
//        {
//
//
//        	FileNameMap fileNameMap = URLConnection.getFileNameMap();
//        	StringBuilder nodePath = new StringBuilder();
//        	nodePath.append(product.getMerchantStore().getCode());
//
//            Node<String, Object> merchantNode = this.getNode(nodePath.toString());
//
//            if ( merchantNode == null )
//            {
//                return null;
//            }
//
//
//            for(String key : merchantNode.getKeys()) {
//
//                byte[] imageBytes = (byte[])merchantNode.get( key );
//
//                OutputContentFile contentImage = new OutputContentFile();
//
//                InputStream input = new ByteArrayInputStream( imageBytes );
//                ByteArrayOutputStream output = new ByteArrayOutputStream();
//                IOUtils.copy( input, output );
//
//                String contentType = fileNameMap.getContentTypeFor( key );
//
//                contentImage.setFile( output );
//                contentImage.setMimeType( contentType );
//                contentImage.setFileName( key );
//
//                images.add( contentImage );
//
//
//            }
//
//
//        }
//
//        catch ( Exception e )
//        {
//            throw new ServiceException( e );
//        }
//        finally
//        {
//
//        }
//
//        return images;
        return null;
    }



	@SuppressWarnings("unchecked")
	@Override
    public void removeImages( final String merchantStoreCode )
        throws ServiceException
    {
//        if ( cacheManager.getTreeCache() == null )
//        {
//            throw new ServiceException( "CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()" );
//        }
//
//        try
//        {
//
//
//			final StringBuilder merchantPath = new StringBuilder();
//	        merchantPath.append( getRootName()).append(merchantStoreCode );
//	        cacheManager.getTreeCache().getRoot().remove(merchantPath.toString());
//
//
//
//        }
//        catch ( Exception e )
//        {
//            throw new ServiceException( e );
//        }
//        finally
//        {
//
//        }


    }


    @Override
    public void removeProductImage( ProductImage productImage )
        throws ServiceException
    {

//        if ( cacheManager.getTreeCache() == null )
//        {
//            throw new ServiceException( "CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()" );
//        }
//
//        try
//        {
//
//
//        	StringBuilder nodePath = new StringBuilder();
//        	nodePath.append(productImage.getProduct().getMerchantStore().getCode()).append(Constants.SLASH).append(productImage.getProduct().getSku());
//
//
//        	Node<String, Object> productNode = this.getNode(nodePath.toString());
//        	productNode.remove(productImage.getProductImage());
//
//
//
//
//
//        }
//        catch ( Exception e )
//        {
//            throw new ServiceException( e );
//        }
//        finally
//        {
//
//        }

    }

    @Override
    public void removeProductImages( Product product )
        throws ServiceException
    {

//        if ( cacheManager.getTreeCache() == null )
//        {
//            throw new ServiceException( "CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()" );
//        }
//
//        try
//        {
//
//
//        	StringBuilder nodePath = new StringBuilder();
//        	nodePath.append(product.getMerchantStore().getCode());
//
//
//        	Node<String, Object> merchantNode = this.getNode(nodePath.toString());
//
//        	merchantNode.remove(product.getSku());
//
//
//
//
//        }
//        catch ( Exception e )
//        {
//            throw new ServiceException( e );
//        }
//        finally
//        {
//
//        }

    }


    @Override
	public List<OutputContentFile> getImages(final String merchantStoreCode,
			FileContentType imageContentType) throws ServiceException {
//        if ( cacheManager.getTreeCache() == null )
//        {
//            throw new ServiceException( "CmsImageFileManagerInfinispan has a null cacheManager.getTreeCache()" );
//        }
//        List<OutputContentFile> images = new ArrayList<OutputContentFile>();
//        FileNameMap fileNameMap = URLConnection.getFileNameMap();
//
//        try
//        {
//
//
//        	StringBuilder nodePath = new StringBuilder();
//        	nodePath.append(merchantStoreCode);
//
//
//        	Node<String, Object> merchantNode = this.getNode(nodePath.toString());
//
//        	Set<Node<String,Object>> childs = merchantNode.getChildren();
//
//        	Iterator<Node<String,Object>> iterator = childs.iterator();
//        	//TODO image sizes
//        	while(iterator.hasNext()) {
//
//        		Node<String,Object> node = iterator.next();
//
//                for(String key : node.getKeys()) {
//
//
//                    byte[] imageBytes = (byte[])merchantNode.get( key );
//
//                    OutputContentFile contentImage = new OutputContentFile();
//
//                    InputStream input = new ByteArrayInputStream( imageBytes );
//                    ByteArrayOutputStream output = new ByteArrayOutputStream();
//                    IOUtils.copy( input, output );
//
//                    String contentType = fileNameMap.getContentTypeFor( key );
//
//                    contentImage.setFile( output );
//                    contentImage.setMimeType( contentType );
//                    contentImage.setFileName( key );
//
//                    images.add( contentImage );
//
//
//                }
//
//        	}
//
//
//
//
//        }
//        catch ( Exception e )
//        {
//            throw new ServiceException( e );
//        }
//        finally
//        {
//
//        }

//        return images;
        return null;
	}

	@Override
	public OutputContentFile getProductImage(String merchantStoreCode,
			String productCode, String imageName) throws ServiceException {
		return getProductImage(merchantStoreCode, productCode, imageName, ProductImageSize.SMALL.name());
	}
	
	@Override
	public OutputContentFile getProductImage(String merchantStoreCode,
			String productCode, String imageName, ProductImageSize size)
			throws ServiceException {
		return getProductImage(merchantStoreCode, productCode, imageName, size.name());
	}
	
	private OutputContentFile getProductImage(String merchantStoreCode,
			String productCode, String imageName, String size) throws ServiceException {
		
        InputStream input = null;
        OutputContentFile contentImage = new OutputContentFile();
        try
        {

        	FileNameMap fileNameMap = URLConnection.getFileNameMap();

        	//SMALL by default
        	StringBuilder nodePath = new StringBuilder();
        	nodePath.append(merchantStoreCode).append(Constants.SLASH).append(productCode).append(Constants.SLASH).append(size);

        	String productNode = nodePath.toString();

            byte[] imageBytes = (byte[]) cacheManager.getCache("default").get(productNode);

            input = new ByteArrayInputStream( imageBytes );
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy( input, output );

            String contentType = fileNameMap.getContentTypeFor( imageName );

            contentImage.setFile( output );
            contentImage.setMimeType( contentType );
            contentImage.setFileName( imageName );


        }
        catch ( Exception e )
        {
            throw new ServiceException( e );
        }
        finally
        {
            if ( input != null )
            {
                try
                {
                    input.close();
                }
                catch ( Exception ignore )
                {
                }
            }
        }

        return contentImage;
		
	}

	
	@SuppressWarnings("unchecked")
//	private Node<String, Object> getNode( final String node )
//    {
//        LOGGER.debug( "Fetching node for store {} from Infinispan", node );
//        final StringBuilder merchantPath = new StringBuilder();
//        merchantPath.append( getRootName() ).append(node);
//
//        Fqn contentFilesFqn = Fqn.fromString(merchantPath.toString());
//
//		Node<String,Object> nd = cacheManager.getTreeCache().getRoot().getChild(contentFilesFqn);
//
//        if(nd==null) {
//
//            cacheManager.getTreeCache().getRoot().addChild(contentFilesFqn);
//            nd = cacheManager.getTreeCache().getRoot().getChild(contentFilesFqn);
//
//        }
//
//        return nd;
//
//    }

	

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getRootName() {
		return rootName;
	}



}
