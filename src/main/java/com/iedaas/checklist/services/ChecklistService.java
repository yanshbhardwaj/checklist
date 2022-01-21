package com.iedaas.checklist.services;

import com.iedaas.checklist.dao.ChecklistOwnerRepository;
import com.iedaas.checklist.dao.ChecklistRepository;
import com.iedaas.checklist.entity.Checklist;
import com.iedaas.checklist.entity.ChecklistOwner;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    ChecklistOwnerRepository checklistOwnerRepository;

    @Transactional
    public void addChecklist(String user, Checklist checklist){

        checklistRepository.save(checklist);
        checklistOwnerRepository.save(new ChecklistOwner(checklist.getChecklistUid(), user));
    }

    @Transactional
    public List<Checklist> getChecklist(){
        return checklistRepository.findAll();
    }

    @Transactional
    public Checklist getChecklistById(String uid){
        return checklistRepository.findbyUUID(uid);
    }

    @Transactional
    public void updateChecklist(String uid,Checklist checklist){
        Checklist checklist1 = checklistRepository.findbyUUID(uid);
        checklist1.setChecklist(checklist.getChecklist());
        checklist1.setStatusId(checklist.getStatusId());
        checklist1.setUpdatedDate();
        checklistRepository.save(checklist1);
    }

    @Transactional
    public void deleteChecklist(String uid){
        checklistRepository.delete(uid);
    }
}
