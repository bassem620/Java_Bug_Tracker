package bug_tracker;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    protected int id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String role;
    protected Timestamp createdAt;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        Date d = new Date();
        Timestamp SQLDate = new Timestamp(d.getTime());
        this.createdAt = SQLDate;
    }
    
    public final void displayUserData() {
        System.out.println("User ID: " + id + ", Username: " + username + ", Name: " + firstName + " " + lastName + ", Role: " + role + ", Created At: " + createdAt);
    }
    
    public final void displayUserData(int iterator) {
        System.out.println("User " + (iterator+1) + ": ID: " + id + ", Username: " + username + ", Name: " + firstName + " " + lastName + ", Role: " + role + ", Created At: " + createdAt);
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final String getRole() {
        return role;
    }

    public final void setRole(String role) {
        this.role = role;
    }

    public final Timestamp getCreatedAt() {
        return createdAt;
    }

    public final void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
 
}
