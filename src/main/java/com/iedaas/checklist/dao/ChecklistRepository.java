package com.iedaas.checklist.dao;

import com.iedaas.checklist.entity.Checklist;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Checklist save(Checklist checklist) {
        return (Checklist) getSession().save(checklist);
    }

    public List<Checklist> pagination(Query<Checklist> query, int page, int size){
        int pageIndex = Math.max(page-1, 0);
        int fromRecordIndex = pageIndex * size;
        int maxRecordIndex = fromRecordIndex + size;
        query.setFirstResult(fromRecordIndex);
        query.setMaxResults(maxRecordIndex);
        return query.list();
    }

    public List<Checklist> findAll(Optional<Integer> page, Optional<Integer> size) {
        Query<Checklist> query = getSession().createNativeQuery("select * from checklist c", Checklist.class);
        if(size.isPresent() && page.isPresent()){
            return pagination(query, page.get(), size.get());
        }
        return query.getResultList();
    }

    public List<Checklist> findAllChecklistRequestChecklist(UUID uid, Optional<Integer> page, Optional<Integer> size){
        Query<Checklist> query = getSession().createNativeQuery("select * from checklist c where c.checklistRequestUid=:uid", Checklist.class);
        query.setParameter("uid", String.valueOf(uid));
        if(size.isPresent() && page.isPresent()){
            return pagination(query, page.get(), size.get());
        }
        return query.getResultList();
    }

    public Checklist findbyUUID(UUID uid){
        Query<Checklist> query = getSession().createNativeQuery("select * from checklist c where c.checklistUid=:uid", Checklist.class);
        query.setParameter("uid", String.valueOf(uid));
        return query.getSingleResult();
    }

    public void delete(UUID uid){
        getSession().delete(findbyUUID(uid));
    }
}
