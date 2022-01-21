package com.iedaas.checklist.entity;

import com.iedaas.checklist.StringMapConverter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "checklist")
public class Checklist {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "checklist_uid")
    @Type(type= "uuid-char")
    private UUID checklistUid;

    @Column(name = "checklist")
    @Convert(converter = StringMapConverter.class)
    private Map<String, String> checklist;

    @Column(name = "checklist_status")
    private int statusId=1;

    @Column(name = "created_date")
    private Timestamp createdDate=Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_date")
    private Timestamp updatedDate=Timestamp.valueOf(LocalDateTime.now());

    public Checklist() {
    }

    public Checklist(UUID checklistUid, Map<String, String> checklist, int statusId, Timestamp createdDate, Timestamp updatedDate) {
        this.checklistUid = checklistUid;
        this.checklist = checklist;
        this.statusId = statusId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public UUID getChecklistUid() {
        return checklistUid;
    }

    public Map<String, String> getChecklist() {
        return checklist;
    }

    public void setChecklist(Map<String, String> checklist) {
        this.checklist = checklist;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    @Override
    public String toString() {
        return "Checklist{" +
                "checklistUid=" + checklistUid +
                ", checklist='" + checklist + '\'' +
                ", statusId=" + statusId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
