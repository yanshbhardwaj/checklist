package com.iedaas.checklist.services;

import com.iedaas.checklist.dao.ChecklistOwnerRepository;
import com.iedaas.checklist.dao.ChecklistRepository;
import com.iedaas.checklist.dto.ChecklistDTO;
import com.iedaas.checklist.entity.Checklist;
import com.iedaas.checklist.entity.ChecklistOwner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    ChecklistOwnerRepository checklistOwnerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ChecklistDTO addChecklist(String user, ChecklistDTO checklistDTO){

        Checklist checklist = modelMapper.map(checklistDTO, Checklist.class);
        checklistRepository.save(checklist);
        ChecklistOwner checklistOwner = new ChecklistOwner(checklist.getChecklistUid(), user);
        checklistOwnerRepository.save(checklistOwner);
        checklistDTO.setChecklistOwner(checklistOwner);
        return checklistDTO;
    }

    @Transactional
    public List<ChecklistDTO> getChecklist(){
        List<ChecklistDTO> checklistDTOS = new ArrayList<>();
        List<Checklist> checklists = checklistRepository.findAll();
        for(Checklist checklist : checklists){
            ChecklistDTO checklistDTO = modelMapper.map(checklist, ChecklistDTO.class);
            checklistDTOS.add(checklistDTO);
        }
        return checklistDTOS;
    }

    @Transactional
    public ChecklistDTO getChecklistById(String uid){
        ChecklistDTO checklistDTO = modelMapper.map(checklistRepository.findbyUUID(uid), ChecklistDTO.class);
        return checklistDTO;
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
