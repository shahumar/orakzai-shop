/**
 * 
 */
package org.orakzai.lab.shop.domain.utils;

import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;

/**
 * @author Umesh A
 *
 */
public interface DataPopulator<Source,Target>
{


    public Target populate(Source source,Target target, MerchantStore store, Language language) throws ConversionException;
    public Target populate(Source source, MerchantStore store, Language language) throws ConversionException;

   
}
