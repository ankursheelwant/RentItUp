package com.project.rentItUp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.rentItUp.pojo.ProductDetails;


public class ProductValidator implements Validator{

	
	public boolean supports(Class aClass)
    {
        return aClass.equals(ProductDetails.class);
    }

	
	public void validate(Object obj, Errors errors)
    {
        ProductDetails product = (ProductDetails) obj;
        System.out.println("Inside Product VALIDATOR!!!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName","error.invalid.productDetails", "Product Name Reuired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productDescription", "error.invalid.productDetails", "Product Description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.productDetails", "Price Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "error.invalid.productDetails", "Category Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isAvailable", "error.invalid.productDetails", "Availability Required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleDetails", "error.invalid.userDetails", "roleDetails object Required");
    }

}
