/**
 * 
 */
package org.orakzai.lab.shop.domain.utils;

import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;

/**
 * @author Umesh A
 *
 */
public interface EntityPopulator<Source,Target>
{

    public Target populateToEntity(Source source, Target target, MerchantStore store)  throws ConversionException;
    public Target populateToEntity(Source source) throws ConversionException;
}
