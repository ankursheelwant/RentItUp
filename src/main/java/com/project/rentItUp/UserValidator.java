package com.project.rentItUp;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.project.rentItUp.pojo.UserDetails;

import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator{

	
	public boolean supports(Class aClass)
    {
        return aClass.equals(UserDetails.class);
    }

	
	public void validate(Object obj, Errors errors)
    {
        UserDetails user = (UserDetails) obj;
        System.out.println("Inside USER VALIDATOR!!!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userDetails", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.userDetails", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNo", "error.invalid.userDetails", "Contact number Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.userDetails", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.userDetails", "Address Required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleDetails", "error.invalid.userDetails", "roleDetails object Required");
    }

}
