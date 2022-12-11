package bug_tracker;

import java.sql.Timestamp;
import java.util.Date;

public final class Project {
    int id;
    String name;
    String desc;
    String createdBy;
    Timestamp createdAt;

    public Project() {
    }

    public Project(String name, String desc, String createdBy) {
        this.name = name;
        this.desc = desc;
        this.createdBy = createdBy;
        Date d = new Date();
        Timestamp SQLDate = new Timestamp(d.getTime());
        this.createdAt = SQLDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
