package com.simi.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simi.pojo.Apply;

public class ApplyValidator implements Validator {

	public boolean supports(Class<?> classz) {
		return Apply.class.equals(classz);
		//return aClass.equals(Apply.class);
	}

	public void validate(Object obj, Errors errors) {
		Apply apply=(Apply) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resume", "error.invalid.apply", "Resume Required");
}
}