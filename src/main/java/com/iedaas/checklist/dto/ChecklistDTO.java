package com.iedaas.checklist.dto;

import com.iedaas.checklist.entity.ChecklistOwner;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

public class ChecklistDTO {

    private UUID checklistUid;
    private Map<Object, Object> checklist;
    private int checklistStatus =1;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private ChecklistOwner checklistOwner;
    private UUID ChecklistRequestUid;

    public UUID getChecklistUid() {
        return checklistUid;
    }

    public void setChecklistUid(UUID checklistUid) {
        this.checklistUid = checklistUid;
    }

    public UUID getChecklistRequestUid() {
        return ChecklistRequestUid;
    }

    public void setChecklistRequestUid(UUID checklistRequestUid) {
        ChecklistRequestUid = checklistRequestUid;
    }

    public Map<Object, Object> getChecklist() {
        return checklist;
    }

    public void setChecklist(Map<Object, Object> checklist) {
        this.checklist = checklist;
    }

    public int getChecklistStatus() {
        return checklistStatus;
    }

    public void setChecklistStatus(int checklistStatus) {
        this.checklistStatus = checklistStatus;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ChecklistOwner getChecklistOwner() {
        return checklistOwner;
    }

    public void setChecklistOwner(ChecklistOwner checklistOwner) {
        this.checklistOwner = checklistOwner;
    }
}
