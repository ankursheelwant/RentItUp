package com.project.rentItUp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.management.relation.RoleStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.rentItUp.dao.ProductDAO;
import com.project.rentItUp.dao.RoleDetailsDAO;
import com.project.rentItUp.dao.UserDAO;
import com.project.rentItUp.exception.AdException;
import com.project.rentItUp.pojo.ProductDetails;
import com.project.rentItUp.pojo.RoleDetails;
import com.project.rentItUp.pojo.UserDetails;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
//	@RequestMapping(value="/adduser.htm", method = RequestMethod.POST)
//	protected String doSubmitAction(@ModelAttribute("user") UserDetails userDetails, BindingResult result) throws Exception {
//		validator.validate(userDetails, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}
//
//		try {
//			System.out.print("test");
//			UserDAO userDao = new UserDAO();
//			System.out.print("test1");
//			
//			userDao.create(userDetails.getUserName(), userDetails.getEmail(), userDetails.getContactNo(), userDetails.getPassword(), userDetails.getAddress());
//			
//			// DAO.close();
//		} catch (AdException e) {
//			System.out.println("Exception: " + e.getMessage());
//		}
//
//		return "addedUser";
//	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") UserDetails userDetails, BindingResult result) {
		return "display";
	}
	
	@RequestMapping(value = "/ngcall.htm", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDetails>> listAllUsers(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("IN THE ngcall");
		ProductDAO prodDAO=null;
		RoleDetailsDAO rolesDAO=null;
		List<ProductDetails> products=null;
//		List<RoleDetails> roles=null;
//		HashMap<String, List> map=null;
		
		//Session trial**************
//		HttpSession session=req.getSession();
//		if(session.getAttribute("user")!=null)
//		{
//			UserDetails ud=(UserDetails)session.getAttribute("user");
//			System.out.println("in Home CONTROLLER: UserName: "+ ud.getUserName());
//		}
//		else
//		{
//			System.out.println("NO SESSION!!!");
//		}
		//*************
		
		try{
		prodDAO=new ProductDAO();
        products = prodDAO.getAll();
        
//        rolesDAO=new RoleDetailsDAO();
//        roles=rolesDAO.getAll();
        
        
//        map=new HashMap<String, List>();
//        map.put("products", products);
        //map.put("roles", roles);
        
        System.out.println("GOT all products ajax");
		}
		catch (AdException e) {
            System.out.println(e.getMessage());
        }
//        if(products.isEmpty()){
//            return new ResponseEntity<List<ProductDetails>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
//        }
        System.out.println("Fetching al prodicts!!!!!!!!!");
        return new ResponseEntity<List<ProductDetails>>(products, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getRoles.htm", method = RequestMethod.GET)
    public ResponseEntity<List<RoleDetails>> listAllRoles(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("IN THE getRoles.html");
		
		RoleDetailsDAO rolesDAO=null;
		
		List<RoleDetails> roles=null;
		
		try{
		
        rolesDAO=new RoleDetailsDAO();
        roles=rolesDAO.getAll();
        
        System.out.println("Got ROLES???: "+roles);
        System.out.println("SIZE of ROLEs list: "+roles.get(0));
        
		}
		catch (AdException e) {
            System.out.println(e.getMessage());
        }
        if(roles.isEmpty()){
            return new ResponseEntity<List<RoleDetails>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<RoleDetails>>(roles, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/checkSession.htm", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Long>> checkSession(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Checking valid session");
		
		HttpSession session=req.getSession(false);
		HashMap<String, Long> map=new HashMap<String, Long>();
		if(session!=null)
		{
			System.out.println("Checking Session: Session is available!!");
			UserDetails user=(UserDetails)session.getAttribute("user");
			Object o=session.getAttribute("roleId");
			if(user!=null && o!=null)
			{
				long userId=user.getUserId();
				map.put("userId", userId);
				long roleId=(Long)o;
				System.out.println("**********RoleId is: "+roleId);
				map.put("roleId", roleId);
				return new ResponseEntity<HashMap<String, Long>>(map, HttpStatus.OK);
			}
			
		}
		return new ResponseEntity<HashMap<String, Long>>(HttpStatus.UNAUTHORIZED);
    }
    

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
}
