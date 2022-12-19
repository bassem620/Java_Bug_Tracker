package bug_tracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public final class Admin extends User implements Admin_Interface{

    public Admin() {
    }

    public Admin(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Admin");
    }
    
    @Override
    public void addUser(String username, String password, String firstName, String lastName, String role) {
        try {
            Date d = new Date();
            Timestamp SQLDate = new Timestamp(d.getTime());  
            Connection con =  connectDB.getConnection();
            String sql = "INSERT INTO employee (username, password, firstName, lastName, role, createdAt) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, role);
            ps.setTimestamp(6, SQLDate);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "New user added : " + username);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't add user");
        }
    }
    
    @Override
    public void updateUser(int id, String username, String firstName, String lastName, String role) {
        try {
          
            Connection con = connectDB.getConnection();
            String sql = "UPDATE employee SET username=?,firstName=?, lastName=?, role=? WHERE id=" + id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, role);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "User " + id + ":" + username + " Updated");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't update user(id:" + id + ") data");
        }
    }
    
    @Override
    public void deleteUser(int id) {
       try { 
            int myID = this.getId();
            if(myID == id){
                JOptionPane.showMessageDialog(null, "You can't delete yourself");
                return;
            }
            Connection con = connectDB.getConnection();
            String check = "select * from employee where id=" + id;
            PreparedStatement ps = con.prepareStatement(check);  
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "User not found");
                return;
            }
            String sql = "delete from employee WHERE id=" + id;
            ps = con.prepareStatement(sql);  
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "User id:" + id + " deleted!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't delete user");
        }
    }
    
        @Override
    public User getUser(int id) {
        User u = new User();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setFirstName(rs.getString("firstName"));
            u.setLastName(rs.getString("lastName"));
            u.setRole(rs.getString("role"));
            u.setCreatedAt(rs.getTimestamp("createdAt"));
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't get user data");
        }
        return u;
    }

    @Override
    public ArrayList<User> searchByUsername(String username) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee WHERE username LIKE \'%" + username +"%\' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int myID = this.getId();
                if(myID == rs.getInt("id"))
                    continue;
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setRole(rs.getString("role"));
                u.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Something went worng!");
        }
        return list;        
    }

    @Override
    public ArrayList<User> searchByName(String firstName) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee WHERE username LIKE \'" + firstName +"%\' ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int myID = this.getId();
                if(myID == rs.getInt("id"))
                    continue;
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setRole(rs.getString("role"));
                u.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Something went worng!");
        }
        return list;       
    }
    
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee order by role";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int myID = this.getId();
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFirstName(rs.getString("firstName"));
                u.setLastName(rs.getString("lastName"));
                u.setRole(rs.getString("role"));
                u.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't fetch data!");
        }
        return list;
    }
    
}
