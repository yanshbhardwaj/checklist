package com.iedaas.checklist.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "checklist_owner")
public class ChecklistOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "checklist_uid", columnDefinition = "VARCHAR(36)")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID checklistUid;

    @Column(name = "owner_uid", columnDefinition = "VARCHAR(36)")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID ownerUid;

    public ChecklistOwner() {
    }

    public ChecklistOwner(UUID checklistUid, UUID ownerUid) {
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

    public UUID getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(UUID ownerUid) {
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
