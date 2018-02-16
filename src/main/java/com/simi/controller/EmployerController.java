package com.simi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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

import com.simi.dao.DAO;
import com.simi.dao.EmployerDAO;
import com.simi.dao.UserDAO;
import com.simi.exception.ApplyException;
import com.simi.exception.EmployerException;
import com.simi.exception.JobPostException;
import com.simi.exception.MessageException;
import com.simi.exception.UserException;
import com.simi.filter.XSSFilter;
import com.simi.pojo.Apply;
import com.simi.pojo.JobPost;
import com.simi.pojo.Message;
import com.simi.pojo.Person;
import com.simi.pojo.User;
import com.simi.validator.JobPostValidator;
import com.simi.validator.UserValidator;

@Controller
@RequestMapping("/employer/*")
public class EmployerController extends DAO{
	
	@Autowired
	@Qualifier("employerDao")
	EmployerDAO employerDao;
	
	@Autowired
	@Qualifier("jobPostValidator")
	JobPostValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/employer/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "employer-success";
	}
	
	@RequestMapping(value = "/employer/search", method = RequestMethod.GET)
	public ModelAndView listEmployee(HttpServletRequest request) throws Exception {

		try {			
			List<Person> person = employerDao.list();
			return new ModelAndView("employee-list", "person", person);
			
		} catch (EmployerException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/employer/searchJobPost", method = RequestMethod.GET)
	public ModelAndView listPostedJobs(HttpServletRequest request) throws Exception {

		try {			
			System.out.println("Inside job post search controller");
			Person person=(Person)request.getSession().getAttribute("user");
			//System.out.println("PERSONID" + person.getPersonID());
			List<JobPost> jobPost = employerDao.listJobPost(person);
			//System.out.println("hi" + jobPost.size());
			return new ModelAndView("jobPostSearch-success", "jobPost", jobPost);
			
		} catch (JobPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying job post list");
		}
		
		
	}
	
	@RequestMapping(value = "/employer/jobPost", method = RequestMethod.GET)
	public ModelAndView addJobPost(HttpServletRequest request) throws Exception {

		//System.out.println("registerUser 1");
		return new ModelAndView("job-post", "jobPost", new JobPost());

		
	}
	
	@RequestMapping(value = "/employer/jobPost", method = RequestMethod.POST)
	public ModelAndView addJobNewPost(HttpServletRequest request,  @ModelAttribute("jobPost") JobPost jobPost, BindingResult result) throws Exception {

		String jobid=jobPost.getJobID();
		String jobID= XSSFilter.removeXSS(jobid);
		jobPost.setJobID(jobID);
		
		String jobtitle=jobPost.getJobTitle();
		String jobTitle= XSSFilter.removeXSS(jobtitle);
		jobPost.setJobTitle(jobTitle);
		
		String companyname=jobPost.getCompanyName();
		String companyName= XSSFilter.removeXSS(companyname);
		jobPost.setCompanyName(companyName);
		
		String desc=jobPost.getDescription();
		String description= XSSFilter.removeXSS(desc);
		jobPost.setDescription(description);
		
		String durat=jobPost.getDuration();
		String duration= XSSFilter.removeXSS(durat);
		jobPost.setDuration(duration);
		
		String loc=jobPost.getLocation();
		String location= XSSFilter.removeXSS(loc);
		jobPost.setLocation(location);
		
		String pos=jobPost.getNoOfPosition();
		String noOfPosition= XSSFilter.removeXSS(pos);
		jobPost.setNoOfPosition(noOfPosition);
		
		//System.out.print("Inside jobpost post method");
		validator.validate(jobPost, result);

		if (result.hasErrors()) {
			return new ModelAndView("job-post", "jobPost", jobPost);
		}

		try {

			System.out.print("Add new job post");
			Date d = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	        String postedOnDate = format.format(d);
	        jobPost.setPostedOn(postedOnDate);
	        Person person=(Person)request.getSession().getAttribute("user");
	        jobPost.setPerson(person);
	        //System.out.println("Person name " + person.getFirstName());
			JobPost jp = employerDao.create(jobPost);
			
			request.getSession().setAttribute("jobPost", jp);
			return new ModelAndView("jobPost-success", "jobPost", jp);
			

		}  catch (JobPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while creating new job post");
		}
		
	}
	
	@RequestMapping(value = "/employer/deletePostedJob", method = RequestMethod.GET)
	public ModelAndView deletePostedJob(HttpServletRequest request) throws Exception {
		System.out.println("apply 1");
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		//System.out.println("Inside employee Controller get method-->" + id);
		try {
			
			System.out.println("Inside employee Controller post method----" + id);
            
			long newid=Long.parseLong(id);
			
			employerDao.delete(newid);
			return new ModelAndView("delete-success");
			

		}  catch (JobPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}
		
	}
	
	@RequestMapping(value = "/employer/updatePostedJobs", method = RequestMethod.GET)
	public ModelAndView updateJobPost(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long jobid=Long.parseLong(id);
		JobPost jobPost=employerDao.updatePosted(jobid);
		request.setAttribute("jobPost", jobPost);
		System.out.println("Update job post" + jobPost.getCompanyName());
		return new ModelAndView("updatePostedJob", "jobPost", jobPost);
		

		
	}
	
	@RequestMapping(value = "/employer/updatePostedJobs", method = RequestMethod.POST)
	public ModelAndView updateExistingJobPost(HttpServletRequest request, @ModelAttribute("jobPost") JobPost jobPost, BindingResult result) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=(String)session.getAttribute("id");
		
		String jobid=jobPost.getJobID();
		String jobID= XSSFilter.removeXSS(jobid);
		jobPost.setJobID(jobID);
		
		String jobtitle=jobPost.getJobTitle();
		String jobTitle= XSSFilter.removeXSS(jobtitle);
		jobPost.setJobTitle(jobTitle);
		
		String companyname=jobPost.getCompanyName();
		String companyName= XSSFilter.removeXSS(companyname);
		jobPost.setCompanyName(companyName);
		
		String desc=jobPost.getDescription();
		String description= XSSFilter.removeXSS(desc);
		jobPost.setDescription(description);
		
		String durat=jobPost.getDuration();
		String duration= XSSFilter.removeXSS(durat);
		jobPost.setDuration(duration);
		
		String loc=jobPost.getLocation();
		String location= XSSFilter.removeXSS(loc);
		jobPost.setLocation(location);
		
		String pos=jobPost.getNoOfPosition();
		String noOfPosition= XSSFilter.removeXSS(pos);
		jobPost.setNoOfPosition(noOfPosition);
	
		System.out.println("Inside update post method");
		validator.validate(jobPost, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("jobPostSearch-success", "jobPost", jobPost);
		}

		try {
			long newid=Long.parseLong(id);
			JobPost jp = employerDao.updateExistingJobPost(jobPost, newid);
			
			request.getSession().setAttribute("jobPost", jp);
			return new ModelAndView("updateJobPost-success", "jobPost", jp);
			

		}  catch (JobPostException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while applying");
		}

	}
	
	@RequestMapping(value = "/employer/viewApplied", method = RequestMethod.GET)
	public ModelAndView listEmployeeApplied(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		String id=request.getParameter("ID");
		session.setAttribute("id", id);
		long jobid=Long.parseLong(id);
		
		try {			
			//System.out.println("Inside job post search controller");
			
			List<Apply> apply = employerDao.listEmployeeApplied(jobid);
			//System.out.println("hi" + jobPost.size());
			return new ModelAndView("employeeAppliedSearch", "apply", apply);
			
		} catch (ApplyException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying job post list");
		}
		
		
	}
	
	
	@RequestMapping(value = "/employer/messageInbox", method = RequestMethod.GET)
	public ModelAndView listMessageInbox(HttpServletRequest request) throws Exception {

		try {			
			Person person=(Person)request.getSession().getAttribute("user");
			System.out.println("PERSONID" + person.getPersonID());
			
			List<Message> message = employerDao.listMessage(person);
			System.out.println("hi message size is-->" + message.size());
			return new ModelAndView("messageListEmployer", "message", message);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while displaying applied jobs");
		}
		
		
	}
	
	
}
