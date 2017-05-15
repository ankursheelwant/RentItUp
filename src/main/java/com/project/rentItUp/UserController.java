package com.project.rentItUp;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.rentItUp.dao.RoleDetailsDAO;
import com.project.rentItUp.dao.UserDAO;
import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.RoleDetails;
import com.project.rentItUp.pojo.UserDetails;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(@Valid @RequestBody UserDetails user, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Creating User " + user.getUserName());
        System.out.println("RoleId submitted: "+user.getRoleId());
        HttpSession session=req.getSession();
        int temp=10;
        try{
            UserDAO userDao=new UserDAO();
            
        if (userDao.isUserExists(user.getUserName())) {
            System.out.println("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Long>(HttpStatus.CONFLICT);
        }
  
        
        UserDetails u= userDao.create(user.getUserName(), user.getEmail(), user.getContactNo(), user.getPassword(), user.getAddress(), user.getRoleId(), user.getRoleDescription());
        
        session.setAttribute("user", u);
        session.setAttribute("roleId", user.getRoleId());
        System.out.println("################roleId added to session: "+user.getRoleId());
        
        }
        catch (AdException e) {
            System.out.println(e.getMessage());
        }
        //user.saveUser(user);
  
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/user/{userId}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Long>(user.getRoleId(),HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/checkLogin.htm", method = RequestMethod.POST)
    public ResponseEntity<UserDetails> checkLogin(@RequestBody UserDetails user, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Checking User " + user.getUserName());
        HttpSession session=req.getSession();
        
        try{
	        UserDAO userDao=new UserDAO();
	            UserDetails userChecked=userDao.checkLogin(user.getUserName(), user.getPassword());
	        if (userChecked!=null) {
	            System.out.println("Login successful for " + userChecked.getUserName());
	            user.setUserId(userChecked.getUserId());
	            session.setAttribute("user", userChecked);
	            
	            
	            return new ResponseEntity<UserDetails>(user,HttpStatus.OK);
	        }
	        
        }
        catch (AdException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Login failed for " + user.getUserName());
        return new ResponseEntity<UserDetails>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/fetchRole.htm", method = RequestMethod.POST)
    public ResponseEntity<Long> fetchRole(@RequestBody Long userId, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Fetching role for userID " + userId);
        HttpSession session=req.getSession(false);
        
        try{
        	if(session!=null)
        	{
	            RoleDetailsDAO roleDAO=new RoleDetailsDAO();
	            long roleId=roleDAO.getRole(userId);
	            if(roleId!=0)
	            {
	            	session.setAttribute("roleId", roleId);
	            	return new ResponseEntity<Long>(roleId, HttpStatus.OK);
	            }
        	}
        }
        catch (AdException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Can't fetch role for: " + userId);
        return new ResponseEntity<Long>(HttpStatus.UNAUTHORIZED);
    }
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
    public ResponseEntity<Void> logout(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Logging OUT!!!");
        HttpSession session=req.getSession(false);
        
        try{
        		session.invalidate();
	            return new ResponseEntity<Void>(HttpStatus.OK);
	            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Can't log out: ");
        return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
    }

}
