package com.simi.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.simi.exception.ApplyException;
import com.simi.exception.EmployeeException;
import com.simi.exception.EmployerException;
import com.simi.exception.JobPostException;
import com.simi.exception.MessageException;
import com.simi.pojo.Apply;
import com.simi.pojo.JobPost;
import com.simi.pojo.Message;
import com.simi.pojo.Person;

public class EmployeeDAO extends DAO{

	public EmployeeDAO(){
	}
	
	public List<JobPost> list() throws EmployeeException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from JobPost");
            System.out.println("hello employee list");
            List<JobPost> jobPost = q.list();
            commit();
            return jobPost;
        } catch (HibernateException e) {
            rollback();
            throw new EmployeeException("Could not find person", e);
        }
    	
    }
	
	public Apply applyJob(Apply apply)
            throws JobPostException {
        try {
            begin();     
            System.out.println("inside Employee DAO for apply");
            getSession().save(apply);  
            
            commit();
            return apply;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create jobPost", e);
            throw new JobPostException("Exception while creating new job post: " + e.getMessage());
        }
    }
	
public List<Apply> listAppiedJobs(Person person) throws ApplyException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long personid=person.getPersonID();
            Query q = getSession().createQuery("from Apply where person_id = '" + personid + "' ");
            System.out.println("hello inside jobpost list DAO below query");
            List<Apply> apply = q.list();
            commit();
            System.out.println("Executed");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find job post", e);
        }
	}
	
	public void delete(long newid)
	        throws ApplyException {
	    try {
	        begin();
	        Query q = getSession().createQuery("delete from Apply where ID = '" + newid + "' ");
	        System.out.println("Inside employee DAo delete method");
	        q.executeUpdate();
	        commit();
	        } catch (HibernateException e) {
	        rollback();
	        throw new ApplyException("Could not delete application", e);
	    }
	}
	
	public Apply updateApplication(long applyid) throws ApplyException{
    	
    	try {
            begin();
            //System.out.println("list DAO for employee applied");
            Query q = getSession().createQuery("from Apply where ID = '" + applyid + "' ");
            //System.out.println("hello inside employee applied list DAO below query");
            Apply apply = (Apply) q.uniqueResult();
            commit();
            System.out.println("Executed here");
            return apply;
        } catch (HibernateException e) {
            rollback();
            throw new ApplyException("Could not find applied employee", e);
        }

    }
	
	public Apply updateExistingApplication(Apply apply, long newid)
            throws ApplyException {
        try {
            begin();     
            String resume=apply.getResume();
            Query q = getSession().createQuery("update Apply set resume ='" + resume + "' where ID = '" + newid + "' ");
            
            q.executeUpdate();
            
            commit();
            return apply;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create jobPost", e);
            throw new ApplyException("Exception while creating new job post: " + e.getMessage());
        }
    }

	public List<Person> listEmployer() throws EmployeeException{
    	
    	try {
            begin();
            /*Query q = getSession().createQuery("from Person where role = :role");
            //System.out.println("hello");
            q.setString("role", "employer");
            List<Person> person = q.list();
            
            commit();
            return person;*/
            
            Criteria cr=getSession().createCriteria(Person.class);
            cr.add(Restrictions.eq("role","employer"));
            List<Person> person = cr.list();
            commit();
            return person;
        } catch (HibernateException e) {
            rollback();
            throw new EmployeeException("Could not find person", e);
        }
    	
    }
	
public List<Message> listMessage(Person person) throws MessageException{
    	
    	try {
            begin();
            System.out.println("list DAO");
            long messageTo=person.getPersonID();
            Query q = getSession().createQuery("from Message where messageTo = '" + messageTo + "' ");
            List<Message> message = q.list();
            System.out.println("Size" + message.size());
            
            commit();
            System.out.println("Executed");
            return message;
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not find job post", e);
        }
	}
	
}
