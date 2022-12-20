package bug_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public final class Project_Manager extends User implements Project_Manager_Interface{

    public Project_Manager() {
        
    }

    public Project_Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Project Manager");
    }

    @Override
    public ArrayList<Bug> monitorBugs() {
        ArrayList<Bug> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql;
            sql = "select id, assignedTo as assigned_Developer, createdBy, project_name, [name], [type], [priority], [level], [status], createdAt from bug where assignedTo is not null";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Bug b = new Bug();
                b.setId(rs.getInt("id"));
                b.setAssignedTo(rs.getString("assigned_Developer"));
                b.setCreatedBy(rs.getString("createdBy"));
                b.setProjectName(rs.getString("project_name"));
                b.setName(rs.getString("name"));
                b.setType(rs.getString("type"));
                b.setPriority(rs.getString("priority"));
                b.setLevel(rs.getString("level"));
                b.setStatus(rs.getString("status"));
                b.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(b); }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Error: Couldn't fetch data!");
        }
        return list;
    }

    @Override
    public ArrayList<Perf> getDevelopersPerformance() {
       ArrayList<Perf> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql;
            sql = "select employee.id,username, firstName+lastName as dev_name, bug.name as bug_name,  project_name,status, bug.createdAt ,finishedAt from employee inner join bug on assignedTo = employee.username";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                Perf d = new Perf();
                d.setId(rs.getInt("id"));
                d.setUsername(rs.getString("username"));
                d.setName(rs.getString("dev_name"));
                d.setBugName(rs.getString("bug_name"));
                d.setProjectName(rs.getString("project_name"));
                d.setStatus(rs.getString("status"));
                d.setCreatedAt(rs.getTimestamp("createdAt"));
                d.setFinishedAt(rs.getTimestamp("finishedAt"));
                list.add(d); }
        }catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Error: Couldn't fetch data!!!");
        }
        return list;
    }
    
    @Override
    public ArrayList<Perf> getTestersPerformance() {
        ArrayList<Perf> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql;
            sql = "select employee.id,username, firstName+lastName as tester_name, bug.name as bug_name,  project_name,status, bug.createdAt ,finishedAt from employee inner join bug on createdBy = employee.username";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
              while(rs.next()){
                Perf d = new Perf();
                d.setId(rs.getInt("id"));
                d.setUsername(rs.getString("username"));
                d.setName(rs.getString("tester_name"));
                d.setBugName(rs.getString("bug_name"));
                d.setProjectName(rs.getString("project_name"));
                d.setStatus(rs.getString("status"));
                d.setCreatedAt(rs.getTimestamp("createdAt"));
                d.setFinishedAt(rs.getTimestamp("finishedAt"));
                list.add(d); }
        }catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Error: Couldn't fetch data!!!");
        }
        return list;
    }

    @Override
     public void createProject(String name, String desc) {
       try {  
            Date d = new Date();
            Timestamp SQLDate = new Timestamp(d.getTime());    
            Connection con =  connectDB.getConnection();
            String sql = "INSERT INTO project (name, description, createdBy, createdAt) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setString(3, this.username);
            ps.setTimestamp(4, SQLDate);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Project added successfully");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Error: Couldn't add new project!");
        }
    }
                
}
