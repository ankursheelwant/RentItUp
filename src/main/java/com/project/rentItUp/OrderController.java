package com.project.rentItUp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import com.project.rentItUp.dao.OrderDAO;
import com.project.rentItUp.dao.ProductDAO;
import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.CartItem;
import com.project.rentItUp.pojo.OrderDetails;
import com.project.rentItUp.pojo.OrderItemDetails;
import com.project.rentItUp.pojo.ProductDetails;
import com.project.rentItUp.pojo.UserDetails;

@Controller
public class OrderController {

	@Autowired
	@Qualifier("orderValidator")
	OrderValidator orderValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(orderValidator);
	}
	
	@RequestMapping(value = "/addtocart.htm", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@RequestBody ProductDetails productItem, HttpServletRequest req, HttpServletResponse res) {
		//CartItem cartItem=new CartItem();
        System.out.println("Adding to cart:- Product: " + productItem.getProductName());
        
        HttpSession session=req.getSession(false);
        
        try{
        	if(session!=null)
    		{
    			System.out.println("Session is available!!");
    			UserDetails user=(UserDetails)session.getAttribute("user");
    			Object obj=session.getAttribute("roleId");
    			
    			OrderDetails order=(OrderDetails)session.getAttribute("Order");
    			List<OrderItemDetails> list=(List<OrderItemDetails>) session.getAttribute("orderItemList");
    			OrderItemDetails orderItem;
    			ProductDetails product=new ProductDetails(user,productItem.getProductName(),productItem.getProductDescription(),productItem.getPrice(),productItem.getCategory(), productItem.isAvailable(),productItem.isApproved());
    			product.setProductId(productItem.getProductId());
    			
    			System.out.println("Product for cart Item(in userController): "+product.getProductId());
    			
    			if(user!=null && obj!=null)
    			{
    				long userId=user.getUserId();
    				long roleId=(Long)obj;
    				if(roleId==3)
    				{
    					System.out.println("User is logged in and is authorised to place order");
    		            OrderDAO orderDAO=new OrderDAO();
    		            float itemAmount=productItem.getQuantity()*productItem.getPrice();
    		            
    		            if(order==null)
    		            {
    		            	System.out.println("Order is not present..Creating new order");
	    		            //Create order to get orderId
	    		            order=orderDAO.createOrder(user);
	    		            session.setAttribute("Order", order);
	    		            
	    		            
	    		            System.out.println("Adding new item to new order cart");
	    		            //Add item to newly created order with generated orderId
	    		            orderItem=orderDAO.addtoCart(order,product, productItem.getQuantity(), itemAmount);
	    		            list=new ArrayList<OrderItemDetails>();
	    		            list.add(orderItem);
	    		            
	    		            session.setAttribute("orderItemList", list);
	    		            return new ResponseEntity<Void>(HttpStatus.OK);
    		            }
    		            else
    		            {
    		            	System.out.println("Unfinished order is available in session");
    		            	boolean itemFlag=false;
//    		            	if(list.size()>0)
//    		            	{
//    		            		Iterator<OrderItemDetails> i=list.listIterator();
//    		            		OrderItemDetails item;
//    		            		System.out.println("Adding productItem: "+productItem.getProductName());
//    		            		while(i.hasNext())
//    		            		{
//    		            			item=i.next();
//    		            			if(item!=null)
//    		            			{
//	    		            			//if(item.getProductId().getProductId()==productItem.getProductId())
//	    		            			//{
//	    		            				System.out.println("Item is present in order..Updating Quantity");
//	    		            				int newQuant=item.getQuantity()+productItem.getQuantity();
//	    		            				float newAmount=newQuant*productItem.getPrice();
//	    		            				//Update quantity in session list
//	    		            				item.setQuantity(newQuant);
//	    		            				item.setPrice(newAmount);
//	    		            				
//	    		            				//update quantity in DB
//	    		            				orderDAO.updateQuantity(item, newQuant,newAmount);
//	    		            				itemFlag=true;
//	    		            				return new ResponseEntity<Void>(HttpStatus.OK);
//	    		            				//break;
//	    		            			//}
//    		            			}
//    		            		}
//    		            		
//    		            	}
//    		            	if(!itemFlag)
//    		            	{
    		            		System.out.println("Order present but item doesnt...creating new item in unfinished order");
    		            		//Add item to newly created order with generated orderId
    	    		            orderItem=orderDAO.addtoCart(order,product, productItem.getQuantity(), itemAmount);
    	    		            
    	    		         
    	    		            list.add(orderItem);
    	    		            session.setAttribute("orderItemList", list);
    	    		            return new ResponseEntity<Void>(HttpStatus.OK);
//    		            	}
    		            }
    		            
    		            
    		            
    		            
    		            
    				}
    				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    			}
    			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    		}
        }
        catch (AdException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }
}
