package com.artenit.appfuse.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.Constants;
import org.appfuse.dao.SearchException;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * Simple class to retrieve a list of users from the database.
 * <p/>
 * <p>
 * <a href="UserController.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
//@RequestMapping("/admin/users*")
public class UserController {
	protected final transient Log log = LogFactory.getLog(getClass());
    private UserManager userManager = null;

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @RequestMapping(value ="/admin/users", method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
        try {
            model.addAttribute(Constants.USER_LIST, userManager.search(query));
            
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(userManager.getUsers());
        }
        return new ModelAndView("admin/userList", model.asMap());
    }
    
    @RequestMapping(value="/admin/users/delete", method = RequestMethod.GET)
    public String onDelete(HttpServletRequest request,  HttpServletResponse response) throws Exception {
    	//Delete one user base on userId
    	String userId = request.getParameter("id");    	
    	if (StringUtils.isNotBlank(userId)){
    		log.debug("Deleting... user with userId: " + userId);
    		User user = userManager.getUser(userId);
    		if (user != null)
    			userManager.removeUser(user);
    		else log.debug("User with id " + userId + " does not exist!!!");
    	}    	    	
    	return "redirect:/admin/users";
    }
    
    
}
