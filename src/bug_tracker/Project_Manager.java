package bug_tracker;

import java.util.ArrayList;

public final class Project_Manager extends User implements Project_Manager_Interface{

    public Project_Manager() {
    }

    public Project_Manager(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Project Manager");
    }

    @Override
    public ArrayList<Developer> getDevelopersPerformance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Tester> getTestersPerformance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void createProject(String name, String desc, String createdBy) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
