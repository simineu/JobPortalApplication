package com.simi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.simi.pojo.*;
import com.simi.exception.ApplyException;
import com.simi.exception.EmployerException;
import com.simi.exception.JobPostException;
import com.simi.exception.MessageException;
import com.simi.exception.UserException;
import org.hibernate.criterion.Restrictions;

public class EmployerDAO extends DAO{
	
	public EmployerDAO(){
	}
	
	public JobPost create(JobPost jobPost)
            throws JobPostException {
        try {
            begin();     
             getSession().save(jobPost);     
            commit();
            return jobPost;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create jobPost", e);
            throw new JobPostException("Exception while creating new job post: " + e.getMessage());
        }
    }
    
    public List<Person> list() throws EmployerException{
    	
    	try {
            begin();
//            Query q = getSession().createQuery("from Person where role = :role");
//            //System.out.println("hello");
//            q.setString("role", "employee");
//            List<Person> person = q.list();
            Criteria cr=getSession().createCriteria(Person.class);
            cr.add(Restrictions.eq("role","employee"));
            List<Person> person = cr.list();
            commit();
            return person;
        } catch (HibernateException e) {
            rollback();
            throw new EmployerException("Could not find person", e);
        }
    	
    }
    
    public List<JobPost> listJobPost(Person person) throws JobPostException{
    	
    	try {
            begin();
            //System.out.println("list DAO");
            long personid=person.getPersonID();
            Query q = getSession().createQuery("from JobPost where person = '" + personid + "' ");
            //System.out.println("hello inside jobpost list DAO below query");
            List<JobPost> jobPost = q.list();
            commit();
            //System.out.println("Executed");
            return jobPost;
        } catch (HibernateException e) {
            rollback();
            throw new JobPostException("Could not find job post", e);
        }

    }
    
    public List<Apply> listEmployeeApplied(long jobid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for employee applied");
            Query q = getSession().createQuery("from Apply where jobPostApply = '" + jobid + "' ");
            //System.out.println("hello inside employee applied list DAO below query");
            List<Apply> apply = q.list();
            commit();
            //System.out.println("Executed");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied employee", e);
        }

    }
    
    /*public JobPost get(long jobid) {
    	begin();
        //System.out.println("list DAO for employee applied");
        Query q = getSession().createQuery("from JobPost where ID = '" + jobid + "' ");
        
        @SuppressWarnings("unchecked")
        List<JobPost> jobPost = (List<JobPost>) q.list();
         
        if (jobPost != null && !jobPost.isEmpty()) {
            return jobPost.get(0);
        }
         
        return (JobPost) jobPost;
    }*/
    
    public JobPost updatePosted(long jobid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for employee applied");
            Query q = getSession().createQuery("from JobPost where ID = '" + jobid + "' ");
            //System.out.println("hello inside employee applied list DAO below query");
            JobPost jobPost = (JobPost) q.uniqueResult();
            commit();
            System.out.println("Executed here");
            return jobPost;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied employee", e);
        }

    }
    
    public void delete(long newid)
            throws JobPostException {
        try {
            begin();
            Query q = getSession().createQuery("delete from JobPost where ID = '" + newid + "' ");
            System.out.println("Inside employee DAo delete method");
            q.executeUpdate();
            commit();
            } catch (HibernateException e) {
            rollback();
            throw new JobPostException("Could not delete jobpost", e);
        }
    }
    
    public JobPost updateExistingJobPost(JobPost jobPost, long newid)
            throws JobPostException {
        try {
            begin();     
            System.out.println("inside Employer DAO to update job post");
            String jobTitle=jobPost.getJobTitle();
            String companyName=jobPost.getCompanyName();
            String decription=jobPost.getDescription();
            String duration=jobPost.getDuration();
            String jobID=jobPost.getJobID();
            String location=jobPost.getLocation();
            String noOfPositions=jobPost.getNoOfPosition();
            String type=jobPost.getType();
            Query q = getSession().createQuery("update JobPost set jobTitle ='" + jobTitle + "', companyName ='" + companyName + "', description ='" + decription + "', duration ='" + duration + "', jobID ='" + jobID + "',location ='" + location + "', noOfPosition ='" + noOfPositions + "', type ='" + type + "'  where ID = '" + newid + "' ");
            
            q.executeUpdate();
            
            //getSession().save(jobPost);        
            commit();
            return jobPost;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create jobPost", e);
            throw new JobPostException("Exception while creating new job post: " + e.getMessage());
        }
    }
    
    
public List<Message> listMessage(Person person) throws MessageException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long messageTo=person.getPersonID();
            Query q = getSession().createQuery("from Message where messageTo = '" + messageTo + "' ");
            System.out.println("hello inside jobpost list DAO below query");
            List<Message> message = q.list();
            commit();
            System.out.println("Executed");
            return message;
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not find job post", e);
        }
	}
}
