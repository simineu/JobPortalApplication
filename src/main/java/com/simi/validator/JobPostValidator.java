package com.simi.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.simi.pojo.JobPost;
import com.simi.pojo.User;

public class JobPostValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(JobPost.class);
	}

	public void validate(Object obj, Errors errors) {
		JobPost jobPost=(JobPost) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobID", "error.invalid.jobPost", "Job ID Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "error.invalid.jobPost", "Job Title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.jobPost", "Company Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "error.invalid.jobPost", "Location Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.jobPost", "Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "error.invalid.jobPost", "Duration Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noOfPosition", "error.invalid.jobPost", "Position Required");
	}
}
