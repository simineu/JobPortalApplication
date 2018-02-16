package com.simi.dao;

import org.hibernate.HibernateException;

import com.simi.exception.MessageException;
import com.simi.pojo.Message;

public class MessageDAO extends DAO{
	
	public Message createMessage(Message message)
            throws MessageException {
        try {
            begin();     
            System.out.println("inside Employee DAO for apply");
            getSession().save(message);  
            
            commit();
            return message;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create jobPost", e);
            throw new MessageException("Exception while creating new job post: " + e.getMessage());
        }
    }

}
