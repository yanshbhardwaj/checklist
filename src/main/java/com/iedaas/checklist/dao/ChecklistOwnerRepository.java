package com.iedaas.checklist.dao;

import com.iedaas.checklist.entity.ChecklistOwner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ChecklistOwnerRepository  {
    @Autowired
    private SessionFactory factory;

    public Session getSession() throws HibernateException {
        Session session = factory.getCurrentSession();
        if (session == null) {
            session = factory.openSession();
        }

        return session;
    }

    public void save(ChecklistOwner checklistOwner) {
        getSession().persist(checklistOwner);
    }
}
