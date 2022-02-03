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
import java.util.Optional;
import java.util.UUID;

@Component
public class ChecklistService {

    @Autowired
    ChecklistRepository checklistRepository;

    @Autowired
    ChecklistOwnerRepository checklistOwnerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ChecklistDTO addChecklist(UUID user, ChecklistDTO checklistDTO){

        Checklist checklist = modelMapper.map(checklistDTO, Checklist.class);
        Checklist checklist1 = checklistRepository.save(checklist);
        ChecklistOwner checklistOwner = new ChecklistOwner(checklist1.getChecklistUid(), user);
        checklistOwnerRepository.save(checklistOwner);
        ChecklistDTO checklistDTO1 = modelMapper.map(checklist1, ChecklistDTO.class);
        checklistDTO1.setChecklistOwner(checklistOwner);
        return checklistDTO1;
    }

    @Transactional
    public List<ChecklistDTO> getChecklist(Optional<Integer> page, Optional<Integer> size){
        List<ChecklistDTO> checklistDTOS = new ArrayList<>();
        List<Checklist> checklists = checklistRepository.findAll(page, size);
        for(Checklist checklist : checklists){
            ChecklistDTO checklistDTO = modelMapper.map(checklist, ChecklistDTO.class);
            checklistDTO.setChecklistOwner(checklistOwnerRepository.getbyUid(checklist.getChecklistUid()));
            checklistDTOS.add(checklistDTO);
        }
        return checklistDTOS;
    }

    @Transactional
    public ChecklistDTO getChecklistById(UUID uid){
        ChecklistDTO checklistDTO = modelMapper.map(checklistRepository.findbyUUID(uid), ChecklistDTO.class);
        checklistDTO.setChecklistOwner(checklistOwnerRepository.getbyUid(uid));
        return checklistDTO;
    }

    @Transactional
    public List<ChecklistDTO> checklistRequestChecklists(UUID uid, Optional<Integer> page, Optional<Integer> size){
        List<ChecklistDTO> checklistDTOS = new ArrayList<>();
        for(Checklist checklist : checklistRepository.findAllChecklistRequestChecklist(uid, page, size)){
            ChecklistDTO checklistDTO = modelMapper.map(checklist, ChecklistDTO.class);
            checklistDTO.setChecklistOwner(checklistOwnerRepository.getbyUid(checklist.getChecklistUid()));
            checklistDTOS.add(checklistDTO);
        }
        return checklistDTOS;
    }

    @Transactional
    public void updateChecklist(UUID uid,Checklist checklist){
        Checklist checklist1 = checklistRepository.findbyUUID(uid);
        checklist1.setChecklist(checklist.getChecklist());
        checklist1.setChecklistStatus(checklist.getChecklistStatus());
        checklist1.setUpdatedDate();
        checklistRepository.save(checklist1);
    }

    @Transactional
    public void deleteChecklist(UUID uid){
        checklistRepository.delete(uid);
    }
}
