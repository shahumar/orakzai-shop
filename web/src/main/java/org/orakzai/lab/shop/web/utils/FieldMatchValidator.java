package org.orakzai.lab.shop.web.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>{

	private String firstFieldName;
	private String secondFieldName;
	private BeanUtils beanUtils;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		this.firstFieldName = constraintAnnotation.first();
		this.secondFieldName = constraintAnnotation.second();
		this.beanUtils = BeanUtils.newInstance();
	}
	
	@SuppressWarnings("nls")
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			final Object firstObj = this.beanUtils.getPropertyValue(value, this.firstFieldName);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
