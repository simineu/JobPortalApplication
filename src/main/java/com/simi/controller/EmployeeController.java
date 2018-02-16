package com.simi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.simi.dao.EmployeeDAO;
import com.simi.dao.EmployerDAO;
import com.simi.exception.ApplyException;
import com.simi.exception.EmployeeException;
import com.simi.exception.EmployerException;
import com.simi.exception.JobPostException;
import com.simi.exception.MessageException;
import com.simi.filter.XSSFilter;
import com.simi.pojo.Apply;
import com.simi.pojo.JobPost;
import com.simi.pojo.Message;
import com.simi.pojo.Person;
import com.simi.validator.ApplyValidator;

@Controller
@RequestMapping("/employee/*")
public class EmployeeController {

	@Autowired
	@Qualifier("employeeDao")
	EmployeeDAO employeeDao;
	
	@Autowired
	@Qualifier("applyValidator")
	ApplyValidator validator;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "employee-success";
	}
	
	@RequestMapping(value = "/employee/jobPostSearch", method = RequestMethod.GET)
	public ModelAndView listEmployee(HttpServletRequest request) throws Exception {
		//HttpSession session = (HttpSession) request.getSession();
		try {	
			List<JobPost> jobPost = employeeDao.list();
			//session.setAttribute("jobPost", jobPost);
			return new ModelAndView("jobPostSearchEmployee-Success", "jobPost", jobPost);
			
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/employee/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		
		String jobid=request.getParameter("jobID");
		session.setAttribute("jobid", jobid);
		
		String jobtitle=request.getParameter("jobTitle");
		session.setAttribute("jobtitle", jobtitle);
		
		String companyname=request.getParameter("companyName");
		session.setAttribute("companyname", companyname);
		
		String description=request.getParameter("description");
		session.setAttribute("description", description);
		
		String location=request.getParameter("location");
		session.setAttribute("location", location);
		
		String duration=request.getParameter("duration");
		session.setAttribute("duration", duration);
		
		String noOfPosition=request.getParameter("noOfPosition");
		session.setAttribute("noOfPosition", noOfPosition);
		
		String postedOn=request.getParameter("postedOn");
		session.setAttribute("postedOn", postedOn);

		String type=request.getParameter("type");
		session.setAttribute("type", type);
		
		System.out.println("JobID in GET" + jobid);
		return new ModelAndView("apply", "apply", new Apply());

		
	}
	
	@RequestMapping(value = "/employee/apply", method = RequestMethod.POST)
	public ModelAndView applyJob(HttpServletRequest request, @ModelAttribute("apply") Apply apply, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		String jobid=(String)session.getAttribute("jobid");
		String jobtitle=(String)session.getAttribute("jobtitle");
		String location=(String)session.getAttribute("location");
		String companyname=(String)session.getAttribute("companyname");
		String description=(String)session.getAttribute("description");
		String duration=(String)session.getAttribute("duration");
		String noOfPosition=(String)session.getAttribute("noOfPosition");
		String type=(String)session.getAttribute("type");
		String postedOn=(String)session.getAttribute("postedOn");
		
		/*String res=apply.getResume();
		String resume= XSSFilter.removeXSS(res);
		apply.setResume(resume);*/
		
		System.out.println("JobID in post " + id + jobid + jobtitle + companyname + description + duration + noOfPosition + postedOn);
		Person person=(Person)request.getSession().getAttribute("user");
		session.getAttribute("user");
		
		
		System.out.println("Inside apply post method");
		validator.validate(apply, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("jobPostSearchEmployee-Success", "apply", apply);
		}

		try {
			long newid=Long.parseLong(id);
			apply.setJobPostApply(newid);
			apply.setPersonApply(person.getPersonID());
			apply.setFirstName(person.getFirstName());
			apply.setLastName(person.getLastName());
			apply.setCompanyName(companyname);
			apply.setDescription(description);
			apply.setDuration(duration);
			apply.setJobID(jobid);
			apply.setJobTitle(jobtitle);
			apply.setLocation(location);
			apply.setNoOfPosition(noOfPosition);
			apply.setPostedOn(postedOn);
			apply.setType(type);
			Apply app = employeeDao.applyJob(apply);
			
			request.getSession().setAttribute("apply", app);
			return new ModelAndView("apply-success", "apply", app);
			

		}  catch (JobPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}
		
	}
	
	@RequestMapping(value = "/employee/searchApplied", method = RequestMethod.GET)
	public ModelAndView listPostedJobs(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Apply> apply = employeeDao.listAppiedJobs(person);
			System.out.println("hi" + apply.size());
			return new ModelAndView("employeeViewApplied", "apply", apply);
			
		} catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied jobs");
		}
		
		
	}
	
	@RequestMapping(value = "/employee/deleteApplication", method = RequestMethod.GET)
	public ModelAndView deleteApplication(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		try {
			
			long newid=Long.parseLong(id);
			
			employeeDao.delete(newid);
			return new ModelAndView("deleteApplicationSuccess");
			

		}  catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while deleting application");
		}
		
	}
	
	
	@RequestMapping(value = "/employee/updateApplication", method = RequestMethod.GET)
	public ModelAndView updateApplication(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long applyid=Long.parseLong(id);
		Apply apply=employeeDao.updateApplication(applyid);
		request.setAttribute("apply", apply);
		return new ModelAndView("applicationUpdate", "apply", apply);
		

		
	}
	
	@RequestMapping(value = "/employee/updateApplication", method = RequestMethod.POST)
	public ModelAndView updateExistingApplication(HttpServletRequest request, @ModelAttribute("apply") Apply apply, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		
		/*String res=apply.getResume();
		String resume= XSSFilter.removeXSS(res);
		apply.setResume(resume);*/
	
		System.out.println("Inside update post method");
		validator.validate(apply, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("employeeAppliedSearch", "apply", apply);
		}

		try {
			long newid=Long.parseLong(id);
			Apply app = employeeDao.updateExistingApplication(apply, newid);
			
			request.getSession().setAttribute("apply", app);
			return new ModelAndView("updateApplicationSuccess", "apply", app);
			

		}  catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}

	}
	
	@RequestMapping(value = "/employee/message", method = RequestMethod.GET)
	public ModelAndView listEmployer(HttpServletRequest request) throws Exception {

		try {			
			List<Person> person = employeeDao.listEmployer();
			return new ModelAndView("listEmployer", "person", person);
			
		} catch (EmployeeException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying employer");
		}
		
		
	}
	
	
	@RequestMapping(value = "/employee/messageInbox", method = RequestMethod.GET)
	public ModelAndView listMessageInbox(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Message> message = employeeDao.listMessage(person);
			System.out.println("hi" + message.size());
			return new ModelAndView("messageList", "message", message);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied jobs");
		}
		
		
	}
	
	
}
