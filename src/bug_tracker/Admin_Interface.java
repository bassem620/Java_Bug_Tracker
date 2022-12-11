package bug_tracker;

import java.util.ArrayList;

public interface Admin_Interface {
    public void addUser(String username, String password, String firstName, String lastName, String role);
    public void updateUser(int id, String username, String password, String firstName, String lastName, String role);
    public void deleteUser(int id);
    public User getUser(int id);
    public ArrayList<User> searchByUsername(String username);
    public ArrayList<User> searchByName(String firstName);
    public ArrayList<User> getAllUsers(String role);
}
