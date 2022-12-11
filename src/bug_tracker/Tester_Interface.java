package bug_tracker;

import java.util.ArrayList;

public interface Tester_Interface {
    public void createBug(String name, String type, String priority, String level, String status, String projectName);
    public void updateBug(String name, String type, String priority, String level, String status, String projectName);
    public void assignBug(int bugID, String DevUsername);
    public void deleteBug(int id);
    public ArrayList<Email> viewEmails();
}
