package bug_tracker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

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
            String ds = d.toString();   
            Connection con =  connectDB.getConnection();
            String sql = "INSERT INTO employee (username, password, firstName, lastName, role, createdAt) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, role);
            ps.setString(6, ds);
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "New user added!");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
    @Override
    public void updateUser(int id, String username, String password, String firstName, String lastName, String role) {
        try {
          
            Connection con = connectDB.getConnection();
            String sql = "UPDATE employee SET username=?,password=?,firstName=?, lastName=?, role=? WHERE id=" + id;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, role);
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Updated!");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
    @Override
    public void deleteUser(int id) {
       try { 
            int myID = this.getId();
            if(myID == id){
                System.out.println("Cannot delete yourself");
                return;
            }
            Connection con = connectDB.getConnection();
            String sql = "delete from employee WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1, id);
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "User id:" + id + "deleted!");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
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
            u.setCreatedAt(rs.getString("createdAt"));
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
        return u;
    }

    @Override
    public ArrayList<User> searchByUsername(String username) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee WHERE username LIKE \'" + username +"%\' ";
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
                u.setCreatedAt(rs.getString("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
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
                u.setCreatedAt(rs.getString("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
        return list;       
    }
    
    @Override
    public ArrayList<User> getAllUsers(String role) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT * FROM employee";
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
                u.setCreatedAt(rs.getString("createdAt"));
                list.add(u); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Error");
        }
        return list;
    }
    
}
