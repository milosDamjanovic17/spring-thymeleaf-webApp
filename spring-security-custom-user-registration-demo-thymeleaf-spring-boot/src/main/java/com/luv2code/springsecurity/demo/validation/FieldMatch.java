package com.luv2code.springsecurity.demo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.luv2code.springsecurity.demo.validation.FieldMatchValidator;

//custom anotacija
@Constraint(validatedBy = FieldMatchValidator.class) // klasa koja sadrzi logiku
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {
	
	String message() default "";
	
	String first();
    String second();
	
	// additional code is mostly boilerplate code to conform to the Spring standards.
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	

    
    // detalji implementacije CrmUser => line 10
    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
    	FieldMatch[] value();
    }
}