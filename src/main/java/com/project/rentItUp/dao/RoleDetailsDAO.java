package com.project.rentItUp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.RoleDetails;
import com.project.rentItUp.pojo.UserDetails;

public class RoleDetailsDAO extends DAO{
	
	public List getAll() throws AdException {
	      try {
	          begin();
	          System.out.println("Fetching ROLES");
	          Query q=getSession().createQuery("select new map(r.roleId as roleId, r.roleDescription as roleDescription) from RoleDetails r");
	          List list = q.list();
	          commit();
	          return list;
	      } catch (HibernateException e) {
	          rollback();
	          throw new AdException("Could not list the RoleDetails", e);
	      }
	  }
	public long getRole(long userID) throws AdException {
	      try {
	          begin();
	          System.out.println("Fetching user specific ROLES");
	          Query q=getSession().createQuery("select r.roleDetails from RoleMapping r where r.userDetails= :userID");
	          
	          q.setLong("userID", userID);
	          RoleDetails roleDetails=(RoleDetails)q.uniqueResult();
	          long roleId = roleDetails.getRoleId();
	            commit();
	            return roleId;
	      } catch (HibernateException e) {
	          rollback();
	          throw new AdException("Could not list the RoleDetails: "+e.getMessage(), e);
	      }
	  }
}
