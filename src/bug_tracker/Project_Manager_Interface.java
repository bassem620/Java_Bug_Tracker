package bug_tracker;

import java.util.ArrayList;

public interface Project_Manager_Interface {
    public ArrayList<Developer> getDevelopersPerformance();
    public ArrayList<Tester> getTestersPerformance();
    public void createProject(String name, String desc, String createdBy);
}
