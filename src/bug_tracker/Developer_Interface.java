package bug_tracker;

import java.util.ArrayList;

public interface Developer_Interface {
    public ArrayList<Bug> getAssignedBugs();
    public void editBugStatus(int id, String status);
    public ArrayList<Email> viewEmails();
}
