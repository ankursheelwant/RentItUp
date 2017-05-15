package com.project.rentItUp.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.OrderDetails;
import com.project.rentItUp.pojo.OrderItemDetails;
import com.project.rentItUp.pojo.ProductDetails;
import com.project.rentItUp.pojo.UserDetails;

public class OrderDAO extends DAO{

	public OrderDetails createOrder(UserDetails user) throws AdException
	{
		try {
        	boolean orderConfirmed=true;
        	float orderAmount=0;
            begin();
            System.out.println("inside Order create");
            Session s=getSession();
            
          //Changes*******
            Query q = s.createQuery("from UserDetails where userId = :userid");
            q.setLong("userid", user.getUserId());
            UserDetails userD = (UserDetails) q.uniqueResult();
            //*******
            
            OrderDetails order=new OrderDetails(userD, orderAmount, new Date(), orderConfirmed);
            
            Serializable i=s.save(order);
            System.out.println("Generated Order id: "+i);
            
            commit();
            return order;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
	}
	public OrderItemDetails addtoCart(OrderDetails order, ProductDetails product,int quantity, float itemAmount)
            throws AdException {
        try {
        	begin();
            System.out.println("inside order item cart creation");
            Session s=getSession();
            
          //Changes*******
            
//            Query q = s.createQuery("from UserDetails where userId = :userid");
//            q.setLong("userid", product.getUser().getUserId());
//            UserDetails userD = (UserDetails) q.uniqueResult();
//            System.out.println("Fetched user:"+userD.getUserId());
            
            System.out.println("Passed in product:"+product.getProductId());
            
            Query qu = s.createQuery("from ProductDetails where productId=:productid");
            qu.setLong("productid", product.getProductId());
            ProductDetails productD = (ProductDetails) qu.uniqueResult();
            System.out.println("Fetched product:"+productD.getProductName());
            
            //productD.setUser(userD);
            //*******
            System.out.println("Extracted product for cart: "+productD.getProductId());//+" & Extracted Product owner :"+userD.getUserName());
            OrderItemDetails orderItem=new OrderItemDetails(order, productD, quantity, itemAmount);
            
            Serializable i=s.save(orderItem);
            System.out.println("Generated order item id: "+i);
            
            commit();
            return orderItem;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }
	
	public int updateQuantity(OrderItemDetails orderItem,int newQuantity, float itemAmount)
            throws AdException {
        try {
        	begin();
            System.out.println("inside update order item quantity");
            Session s=getSession();
            
            Query q=s.createQuery("Update OrderItemDetails set quantity=:quant and itemAmount=:amount where itemId=:itemId");
            q.setInteger("quant", newQuantity);
            q.setFloat("amount", itemAmount);
            q.setLong("itemId", orderItem.getItemId());
            int result=q.executeUpdate();
            
            System.out.println("Rows affected: "+result);
            
            commit();
            return result;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }
}
