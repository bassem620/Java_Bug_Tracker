package bug_tracker;

public final class Tester extends User implements Tester_Interface{

    public Tester() {
    }

    public Tester(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName, "Tester");
    }

    @Override
    public void createBug(String name, String type, String priority, String level, String status, String assignedTo, String projectName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateBug(String name, String type, String level, String status, String assignedTo, String projectName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteBug(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void assignBug(int bugID, String DevUsername) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Email> viewEmails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } 
    
}
