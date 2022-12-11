package bug_tracker;

import java.util.ArrayList;

public final class Developer extends User implements Developer_Interface{
    
    public Developer() {
    }

    public Developer(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Developer");
    }

    @Override
    public ArrayList<Bug> getAssignedBugs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editBugStatus(int id, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Email> viewEmails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
