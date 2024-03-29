package com.iedaas.checklist.entity;

import com.iedaas.checklist.StringMapConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "checklist")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checklistId;

    @Column(name = "checklist_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID checklistUid=UUID.randomUUID();

    @Column(name = "checklist")
    @Convert(converter = StringMapConverter.class)
    private Map<Object, Object> checklist;

    @Column(name = "checklist_status")
    private int checklistStatus=1;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate=Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate=Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "checklist_request_uid", columnDefinition = "VARCHAR(36)")
    @Type(type= "org.hibernate.type.UUIDCharType")
    UUID checklistRequestUid;

    public Checklist() {
    }

    public Checklist(int checklistId, UUID checklistUid, Map<Object, Object> checklist,
                     int checklistStatus, Timestamp createdDate, Timestamp updatedDate, UUID checklistRequestUid) {
        this.checklistId = checklistId;
        this.checklistUid = checklistUid;
        this.checklist = checklist;
        this.checklistStatus = checklistStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.checklistRequestUid = checklistRequestUid;
    }

    public int getChecklistId() {
        return checklistId;
    }

    public UUID getChecklistUid() {
        return checklistUid;
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

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate() {
        this.updatedDate = updatedDate;
    }

    public UUID getChecklistRequestUid() {
        return checklistRequestUid;
    }

    public void setChecklistRequestUid(UUID checklistRequestUid) {
        this.checklistRequestUid = checklistRequestUid;
    }

    @Override
    public String toString() {
        return "Checklist{" +
                "checklistId=" + checklistId +
                ", checklistUid=" + checklistUid +
                ", checklist=" + checklist +
                ", checklistStatus=" + checklistStatus +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", checklistRequestUid=" + checklistRequestUid +
                '}';
    }
}
