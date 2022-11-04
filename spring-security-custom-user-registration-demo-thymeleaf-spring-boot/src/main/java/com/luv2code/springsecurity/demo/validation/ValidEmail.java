package com.luv2code.springsecurity.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// custom anotacija
@Constraint(validatedBy = EmailValidator.class) // klasa koja sadrzi logiku
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE }) // ukoliko ima ElementType.FIELD, onda se anotacija primenjuje na neki field/polje
@Retention(RetentionPolicy.RUNTIME) // u runtimeu se izvrsava anotacija
@Documented
public @interface ValidEmail {
	// message() is the error message that is showed in the user interface
	String message() default "Invalid email";

	// additional code is mostly boilerplate code to conform to the Spring standards.
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
