package com.iedaas.checklist.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "checklist_owner")
public class ChecklistOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private UUID checklistUid;
    private String ownerUid;

    public ChecklistOwner() {
    }

    public ChecklistOwner(UUID checklistUid, String ownerUid) {
        this.checklistUid = checklistUid;
        this.ownerUid = ownerUid;
    }

    public int getId() {
        return id;
    }

    public UUID getChecklistUid() {
        return checklistUid;
    }

    public void setChecklistUid(UUID checklistUid) {
        this.checklistUid = checklistUid;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    @Override
    public String toString() {
        return "ChecklistOwner{" +
                "id=" + id +
                ", checklistUid=" + checklistUid +
                ", ownerUid=" + ownerUid +
                '}';
    }
}
