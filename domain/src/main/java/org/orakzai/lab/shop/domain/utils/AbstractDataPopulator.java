/**
 * 
 */
package org.orakzai.lab.shop.domain.utils;

import java.util.Locale;

import org.orakzai.lab.shop.domain.business.generic.exception.ConversionException;
import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.reference.language.model.Language;


public abstract class AbstractDataPopulator<Source,Target> implements DataPopulator<Source, Target>
{

 
   
    private Locale locale;

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Locale getLocale() {
		return locale;
	}
	

	@Override
	public Target populate(Source source, MerchantStore store, Language language) throws ConversionException{
	   return populate(source,createTarget(), store, language);
	}
	
	protected abstract Target createTarget();

   

}
