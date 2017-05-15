package com.project.rentItUp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.RoleDetails;
import com.project.rentItUp.pojo.RoleMapping;
//import com.yusuf.spring.pojo.Email;
//import com.yusuf.spring.pojo.Person;
import com.project.rentItUp.pojo.UserDetails;

public class UserDAO extends DAO {

    public UserDAO() {
    }

    public UserDetails get(String username)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from UserDetails where userName = :username");
            q.setString("username", username);
            UserDetails user = (UserDetails) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            System.out.println("Exception:"+e.getMessage());
            throw new AdException("Could not get user " + username, e);
        }
    }

    public UserDetails create(String userName, String email,long contactNo, String password, String address, long roleId, String roleDescription)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            Session s=getSession();
            UserDetails user=new UserDetails(userName, email, contactNo, password, address);
            RoleDetails roleDetails=new RoleDetails(roleId, roleDescription);
            RoleMapping roleMap=new RoleMapping(user, roleDetails);
            System.out.print("RoleDescription!!!!!: "+roleDetails.getRoleDescription());
            Serializable i=s.save(user);
            System.out.println("Generated UserId:"+i);
            Serializable j=s.save(roleMap);
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }
    
    public boolean isUserExists(String userName) throws AdException{
    	try
    	{
    	UserDetails user=get(userName);
    	if(user!=null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	}
    	catch(HibernateException e) {
            rollback();
            throw new AdException("Exception while checking user " + e.getMessage());
        }
    }
    public UserDetails checkLogin(String userName, String password)
    	    throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from UserDetails u where u.userName = :username and u.password = :pass");
            q.setString("username", userName);
            q.setString("pass", password);
            UserDetails user = (UserDetails)q.uniqueResult();
            //System.out.println("selecte userId: "+user.getUserId());
            commit();
            return user;
            
            
        } catch (HibernateException e) {
            rollback();
            System.out.println("Exception:"+e.getMessage());
            throw new AdException("Could not get user " + userName, e);
        }
    }

    public void delete(UserDetails user)
            throws AdException {
        try {
            begin();
            getSession().delete(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user ", e);
        }
    }
}