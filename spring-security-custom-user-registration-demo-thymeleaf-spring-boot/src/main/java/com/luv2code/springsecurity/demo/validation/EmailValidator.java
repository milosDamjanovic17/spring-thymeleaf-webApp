package com.luv2code.springsecurity.demo.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * logika custom anotacije, klasa koja ispituje da li su polja ista, mora da implementira ConstraintValidator interface<A, T>
 * 
 * <A> the annotation type handled by an implementation, <T> the target type supported by an implementation
 * 
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private Pattern pattern;
	private Matcher matcher;
	// regex uslov za validaciju maila, ne sme da se dozvoli da se ovaj field menja
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		if (email == null) {
			return false;
		}
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}