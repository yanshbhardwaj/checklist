package com.iedaas.checklist.controller;

import com.iedaas.checklist.AuthorizationFilter;
import com.iedaas.checklist.dto.ChecklistDTO;
import com.iedaas.checklist.entity.Checklist;
import com.iedaas.checklist.services.ChecklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ChecklistController {

    private static final Logger logger = LoggerFactory.getLogger(ChecklistController.class);

    @Autowired
    private ChecklistService checklistService;

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @GetMapping("/checklist")
    public List<ChecklistDTO> getChecklist(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        logger.info("Calling the services getAll method");
        return checklistService.getChecklist(page, size);
    }

    @GetMapping("/checklist/{uid}")
    public ChecklistDTO getChecklistById(@PathVariable UUID uid){
        logger.info("Calling the services getByUid method");
        return checklistService.getChecklistById(uid);
    }

    @GetMapping("/checklist/checklistRequest/{uid}")
    public List<ChecklistDTO> getChecklistRequestChecklists(@PathVariable UUID uid, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        logger.info("Calling the services getAll method");
        return checklistService.checklistRequestChecklists(uid, page, size);
    }

    @PostMapping("/checklist")
    public ChecklistDTO addChecklist(HttpServletRequest request, @RequestBody ChecklistDTO checklistDTO){
        UUID userUid = UUID.fromString(authorizationFilter.authenticate(request));
        return checklistService.addChecklist(userUid, checklistDTO);
    }

    @PutMapping("/checklist/{uid}")
    public void updateChecklist(@PathVariable UUID uid, @RequestBody Checklist checklist){
        checklistService.updateChecklist(uid, checklist);
    }

    @DeleteMapping("/checklist/{uid}")
    public String deleteChecklist(@PathVariable UUID uid){
        checklistService.deleteChecklist(uid);
        return "Deleted Successfully";
    }
}
