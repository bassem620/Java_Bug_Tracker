package bug_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public final class Bug {
    protected int id;
    protected String name;
    protected String type;
    protected String priority;
    protected String level;
    protected String status;
    protected String createdBy;
    protected String assignedTo;
    protected String projectName;
    protected String createdAt;
    protected String finishedAt;

    public Bug() {
    }

    public Bug(String name, String type, String priority, String level, String status, String createdBy, String assignedTo, String projectName) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.level = level;
        this.status = status;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.projectName = projectName;
        Date d = new Date();
        this.createdAt = d + "";
        this.finishedAt = "Not Yet";
    }
    
    public final static ArrayList<Bug> getAllBugs() { 
        ArrayList<Bug> list = new ArrayList<Bug>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM bug";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Bug b = new Bug();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setType(rs.getString("type"));
                b.setPriority(rs.getString("priority"));
                b.setLevel(rs.getString("level"));
                b.setStatus(rs.getString("status"));
                b.setCreatedBy(rs.getString("createdBy"));
                b.setAssignedTo(rs.getString("assignedTo"));
                b.setProjectName(rs.getString("project_name"));
                b.setCreatedAt(rs.getString("createdAt"));
                b.setFinishedAt(rs.getString("finishedAt"));
                list.add(b); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
        return list;
    }
    
    public final void displayBugData() {
        System.out.println("Bug ID: " + id + ", Name: " + name + ", Type: " + type + ", Level: " + level + ", Status: " + status + 
                ", Created By: " + createdBy + ", Assigned To: " + assignedTo + ", Project Name: " + projectName + ", Created At: " + createdAt + ", Finished At: " + finishedAt);
    }
    
    public final void displayBugData(int i) {
        System.out.println("Bug " + (i+1) + ": ID: " + id + ", Name: " + name + ", Type: " + type + ", Level: " + level + ", Status: " + status + 
                ", Created By: " + createdBy + ", Assigned To: " + assignedTo + ", Project Name: " + projectName + ", Created At: " + createdAt + ", Finished At: " + finishedAt);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }
    
    
}
