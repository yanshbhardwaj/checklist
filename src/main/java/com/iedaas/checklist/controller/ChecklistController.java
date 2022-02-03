package com.iedaas.checklist.controller;

import com.iedaas.checklist.dto.ChecklistDTO;
import com.iedaas.checklist.entity.Checklist;
import com.iedaas.checklist.services.ChecklistService;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ChecklistController {

    private static final Logger logger = LoggerFactory.getLogger(ChecklistController.class);

    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/checklist")
    public List<ChecklistDTO> getChecklist(){
        logger.info("Calling the services getAll method");
        return checklistService.getChecklist();
    }

    @GetMapping("/checklist/{uid}")
    public ChecklistDTO getChecklistById(@PathVariable String uid){
        logger.info("Calling the services getByUid method");
        return checklistService.getChecklistById(uid);
    }

    @PostMapping("/checklist")
    public ChecklistDTO addChecklist(HttpServletRequest request, @RequestBody ChecklistDTO checklistDTO){
        String token = request.getHeader(HEADER_STRING);
        String user = null;
        if (token != null) {
            // parse the token.
            System.out.println(request.getHeader(HEADER_STRING));

            try {
                user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return checklistService.addChecklist(user, checklistDTO);
    }

    @PutMapping("/checklist/{uid}")
    public void updateChecklist(@PathVariable String uid, @RequestBody Checklist checklist){
        checklistService.updateChecklist(uid, checklist);
    }

    @DeleteMapping("/checklist/{uid}")
    public String deleteChecklist(@PathVariable String uid){
        checklistService.deleteChecklist(uid);
        return "Deleted Successfully";
    }
}
