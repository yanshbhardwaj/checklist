package com.iedaas.checklist.dao;

import com.iedaas.checklist.entity.ChecklistOwner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


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

    public ChecklistOwner getbyUid(UUID uid){
        Query query = getSession().createNativeQuery("select * from checklist_owner o where o.checklist_uid=:uid", ChecklistOwner.class);
        query.setParameter("uid", String.valueOf(uid));
        return (ChecklistOwner) query.getSingleResult();
    }
}
