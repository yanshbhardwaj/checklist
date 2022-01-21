package com.iedaas.checklist.dao;

import com.iedaas.checklist.entity.Checklist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class ChecklistRepository {

    @Autowired
    private SessionFactory factory;

    public Session getSession() throws HibernateException {
        Session session = factory.getCurrentSession();
        if (session == null) {
            session = factory.openSession();
        }

        return session;
    }

    public void save(Checklist checklist) {
        getSession().save(checklist);
    }

    public List<Checklist> findAll() {
        return getSession().createSQLQuery("SELECT * FROM checklist").getResultList();
    }

    public Checklist findbyUUID(String uid){
        return getSession().get(Checklist.class, UUID.fromString(uid));
    }

    public void delete(String uid){
        getSession().delete(findbyUUID(uid));
    }
}
