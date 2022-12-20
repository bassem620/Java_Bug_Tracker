package bug_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public final class Developer extends User implements Developer_Interface{
    
    public Developer() {
    }

    public Developer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Developer");
    }

    @Override
    public ArrayList<Bug> getAssignedBugs() { 
        ArrayList<Bug> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM bug where assignedTo=\'"+ this.username+"\'";
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
                b.setCreatedAt(rs.getTimestamp("createdAt"));
                b.setFinishedAt(rs.getTimestamp("finishedAt"));
                list.add(b); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't fetch assigned bugs data!");
        }
        return list;
    }
    

    @Override
    public void editBugStatus(int id, String status) {
       try {  
            Connection con = connectDB.getConnection();
            String sql = "UPDATE bug SET status=\'" + status + "\' WHERE id=" + id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bug status updated");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't update bug status");
        }
    }
        

    @Override
    public ArrayList<Email> viewEmails() { 
        ArrayList<Email> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM Email where [to]=\'"+ this.username + "\'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Email a= new Email();
                a.setId(rs.getInt("id"));
                a.setSender(rs.getString("Sender"));
                a.setTo(rs.getString("To"));
                a.setMessage(rs.getString("Message"));
                a.setCreatedAt(rs.getTimestamp("CreatedAt"));
                list.add(a); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't fetch emails data!");
        }
        return list;
    }
    }