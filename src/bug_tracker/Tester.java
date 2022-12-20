package bug_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public final class Tester extends User implements Tester_Interface{

    public Tester() {
    }

    public Tester(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Tester");
    }

    @Override
    public void createBug(String name, String type, String priority, String level, String status, String projectName) {
        try {
            Date d = new Date();
            Timestamp SQLDate = new Timestamp(d.getTime());  
            Connection con =  connectDB.getConnection();
            String sql = "INSERT INTO bug (name, type, priority, level, status, project_name, createdAt, createdBy) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, priority);
            ps.setString(4, level);
            ps.setString(5, status);
            ps.setString(6, projectName);
            ps.setTimestamp(7, SQLDate);
            ps.setString(8, this.username);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "New bug created");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't create bug");
        }
    }

    @Override
    public void updateBug(int id, String name, String type, String priority, String level, String status, String projectName) {
        try {
            Connection con =  connectDB.getConnection();
            String sql = "UPDATE bug set name=?, type=?, priority=?, level=?, status=?, project_name=? where id=" + id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, priority);
            ps.setString(4, level);
            ps.setString(5, status);
            ps.setString(6, projectName);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bug id:"+ id +  " updated ");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't update bug data");
        }
    }

    @Override
    public void assignBug(int bugID, String DevUsername, String message) {
        // Check if username exist
        try{
            Connection con =  connectDB.getConnection();
            String sql = "select * from employee where username=\'" + DevUsername + "\'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            // check if username is a developer
            if(!rs.getString("role").equals("Developer")){
                JOptionPane.showMessageDialog(null, "This username is not a developer!");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't find user!");
            return; // to ignore the rest of the function
        }
        // Try to assign bug to developer
        try {
            Connection con =  connectDB.getConnection();
            String sql = "UPDATE bug set assignedTo=? where id=" + bugID;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, DevUsername);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bug id:"+ id +  " is assigned to:" + DevUsername);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't update bug data");
            return; // to ignore the rest of the function
        }
        // Try to send email to developer
        try{
            Date d = new Date();
            Timestamp SQLDate = new Timestamp(d.getTime());  
            Connection con =  connectDB.getConnection();
            String sql = "INSERT INTO email(sender, [to], message, createdAt) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.username);
            ps.setString(2, DevUsername);
            ps.setString(3, message);
            ps.setTimestamp(4, SQLDate);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Email is sent to:" + DevUsername);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't send email");
        }
    }

    @Override
    public void deleteBug(int id) {
        try {
            Connection con =  connectDB.getConnection();
            String sql = "DELETE bug where id=" + id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Bug id:"+ id +  " deleted! ");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't delete bug");
        }
    }

    @Override
    public ArrayList<Email> viewEmails() {
        ArrayList<Email> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM email WHERE [to]=\'" + this.username + "\'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Email e = new Email();
                e.setId(rs.getInt("id"));
                e.setSender(rs.getString("sender"));
                e.setTo(rs.getString("to"));
                e.setMessage(rs.getString("message"));
                e.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Something went worng!");
        }
        return list;
    }
    
}
