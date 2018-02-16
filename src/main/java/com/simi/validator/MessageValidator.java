package com.simi.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simi.pojo.Message;

public class MessageValidator implements Validator {

	public boolean supports(Class<?> classz) {
		return Message.class.equals(classz);
		//return aClass.equals(Apply.class);
	}

	public void validate(Object obj, Errors errors) {
		Message message=(Message) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.message", "Message Required");
}
}
