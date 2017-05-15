package com.project.rentItUp.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.ProductDetails;
import com.project.rentItUp.pojo.RoleDetails;
import com.project.rentItUp.pojo.RoleMapping;
import com.project.rentItUp.pojo.UserDetails;

public class ProductDAO extends DAO{

//	public ProductDetails get(String title) throws AdException {
//      try {
//          begin();
//          Query q=getSession().createQuery("from ProductDetails");
//
//          ProductDetails product=(ProductDetails)q.uniqueResult();
//          commit();
//          return product;
//      } catch (HibernateException e) {
//          rollback();
//          throw new AdException("Could not obtain the named category " + title + " " + e.getMessage());
//      }
//  }
	public List<ProductDetails> getAll() throws AdException {
      try {
          begin();
          Query q=getSession().createQuery("select new map(p.productId as productId, p.productName as productName, p.productDescription as productDescription, p.price as price, p.category as category, p.isAvailable as isAvailable, p.isApproved as isApproved ) from ProductDetails p");
          //from ProductDetails where isApproved=:approved
          //q.setBoolean("approved", true);
          List<ProductDetails> list = q.list();
          commit();
          return list;
      } catch (HibernateException e) {
          rollback();
          System.out.println(e.getMessage());
          throw new AdException("Could not list the ProductDetails", e);
      }
  }
	
	public ProductDetails create(UserDetails user, String productName, String productDescription, float price, String category, boolean isAvailable)
            throws AdException {
        try {
        	boolean isApproved=false;
            begin();
            System.out.println("inside Product create");
            Session s=getSession();
            System.out.println("Available user for Product: "+user.getUserName());
            
            
            //Changes*******
            Query q = s.createQuery("from UserDetails where userId = :userid");
            q.setLong("userid", user.getUserId());
            UserDetails userD = (UserDetails) q.uniqueResult();
            //*******
            ProductDetails product=new ProductDetails(userD, productName, productDescription, price, category, isAvailable, isApproved);
            System.out.println("Available user2 for Product: "+user.getUserName());
            //System.out.println("Hibernate Session: "+ getSession());
            Serializable i=s.save(product);
            
            System.out.println("Generated productId: "+i);
            
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Hibernate Exception while creating Product: " + e.getMessage());
        }
    }
}
