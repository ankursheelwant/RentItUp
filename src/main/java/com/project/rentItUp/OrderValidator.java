package com.project.rentItUp;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.rentItUp.pojo.CartItem;


public class OrderValidator implements Validator{

	
	public boolean supports(Class aClass)
    {
        return aClass.equals(CartItem.class);
    }

	
	public void validate(Object obj, Errors errors)
    {
		CartItem cartItem = (CartItem) obj;
        System.out.println("Inside ORDER VALIDATOR!!!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productDetails","error.invalid.cartItemDetails", "Product Details Reuired");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.invalid.cartItemDetails", "Quantity Required");
        
    }

}
