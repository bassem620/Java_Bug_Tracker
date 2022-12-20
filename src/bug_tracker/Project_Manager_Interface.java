package bug_tracker;

import java.util.ArrayList;

public interface Project_Manager_Interface {
    public ArrayList<Bug> monitorBugs();
    public ArrayList<Perf> getDevelopersPerformance();
    public ArrayList<Perf> getTestersPerformance();
    public void createProject(String name, String desc);
}
