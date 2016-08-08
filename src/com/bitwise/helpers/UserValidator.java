package com.bitwise.helpers;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bitwise.models.User;


public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		return User.class.equals(obj.getClass());
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "errors.username", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.password", "Password is required");
		
		boolean isNotValidLength = user.getUsername().length() < 3;
		if (isNotValidLength)
			errors.rejectValue("username", "insufficient", "Username must be atleast 3 chars long");
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		boolean isValidPassword = user.getPassword().matches(regex);
		
		String errorMessage = "At least 8 chars\n"+
"Contains at least one digit \n" +
"Contains at least one lower alpha char and one upper alpha char\n"+
"Contains at least one char within a set of special chars (@#%$^ etc.)\n"+
"Does not contain space, tab, etc";
		if (! isValidPassword)
			errors.rejectValue("password", "invalidPassword", errorMessage);
					
	}

}
