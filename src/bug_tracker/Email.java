package bug_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public final class Email {
    int id;
    String sender;
    String to;
    String message;
    Timestamp createdAt;

    public Email() {
    }

    public Email(String sender, String to, String message) {
        this.sender = sender;
        this.to = to;
        this.message = message;
        Date d = new Date();
        Timestamp SQLDate = new Timestamp(d.getTime());
        this.createdAt = SQLDate;
    }
    
    public static ArrayList<Email> myEmails(int username) {
        ArrayList<Email> list = new ArrayList<>();
        try {
            Connection con = connectDB.getConnection();
            String sql = "SELECT id, sender, message, createdAt FROM email WHERE to=" + username;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Email e = new Email();
                e.setId(rs.getInt("id"));
                e.setSender(rs.getString("sender"));
                e.setMessage(rs.getString("message"));
                e.setCreatedAt(rs.getTimestamp("createdAt"));
                list.add(e); 
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            JOptionPane.showMessageDialog(null, "Couldn't fetch emails data!");
        }
        return list;      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    

}
