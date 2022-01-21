package com.iedaas.checklist.controller;

import com.iedaas.checklist.entity.Checklist;
import com.iedaas.checklist.services.ChecklistService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ChecklistController {

    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Autowired
    ChecklistService checklistService;

    @GetMapping("/checklist")
    public List<Checklist> getChecklist(){

        return checklistService.getChecklist();
    }

    @GetMapping("/checklist/{uid}")
    public Checklist getChecklistById(@PathVariable String uid){
        return checklistService.getChecklistById(uid);
    }

    @PostMapping("/checklist")
    public String addChecklist(HttpServletRequest request, @RequestBody Checklist checklist){
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
        checklistService.addChecklist(user, checklist);
        return "Checklist Inserted Successfully";
    }

    @PutMapping("/checklist/{uid}")
    public String updateChecklist(@PathVariable String uid, @RequestBody Checklist checklist){
        checklistService.updateChecklist(uid, checklist);
        return "Checklist Updated Successfully";
    }

    @DeleteMapping("/checklist/{uid}")
    public String deleteChecklist(@PathVariable String uid){
        checklistService.deleteChecklist(uid);
        return "Deleted Successfully";
    }
}
