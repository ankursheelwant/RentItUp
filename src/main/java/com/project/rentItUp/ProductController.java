package com.project.rentItUp;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.rentItUp.dao.ProductDAO;
import com.project.rentItUp.dao.UserDAO;
import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.ProductDetails;
import com.project.rentItUp.pojo.UserDetails;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productValidator")
	ProductValidator productValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(productValidator);
	}
	
	@RequestMapping(value = "/upload.htm", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@Valid @RequestBody ProductDetails product, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Uploading Product " + product.getProductName());
        
        HttpSession session=req.getSession(false);
        
        try{
        	if(session!=null)
    		{
    			System.out.println("Session is available!!");
    			UserDetails user=(UserDetails)session.getAttribute("user");
    			Object obj=session.getAttribute("roleId");
    			if(user!=null && obj!=null)
    			{
    				long userId=user.getUserId();
    				long roleId=(Long)obj;
    				if(roleId==2)
    				{
    					ProductDAO productDAO=new ProductDAO();
    		            
    		            productDAO.create(user, product.getProductName(), product.getProductDescription(), product.getPrice(), product.getCategory() , product.isAvailable());
    		            return new ResponseEntity<Void>(HttpStatus.OK);
    				}
    				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    			}
    		}
        }
        catch (AdException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }
}
