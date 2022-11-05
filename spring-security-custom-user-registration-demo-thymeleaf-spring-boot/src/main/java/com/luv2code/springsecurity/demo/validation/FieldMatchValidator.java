package com.luv2code.springsecurity.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

/*
 * logika custom anotacije, klasa koja ispituje da li su polja ista, mora da implementira ConstraintValidator interface<A, T>
 * 
 * <A> the annotation type handled by an implementation, <T> the target type supported by an implementation
 * 
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
	    	firstFieldName = constraintAnnotation.first(); // bindujemo za polje u @interface FieldMatch, u ovom slucaju je String first
	    	secondFieldName = constraintAnnotation.second(); // bindujemo za polje u @interface FieldMatch, u ovom slucaju je String second
	    	message = constraintAnnotation.message(); 	// bindujemo za polje u @interface FieldMatch, u ovom slucaju je String message
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try
        {
            final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }
        catch (final Exception ignore)
        {
            // we can ignore
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
	
}