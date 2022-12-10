
public class Server {

    private final String serverID;
    private final String location;
    private final String role;
    private final String fault;
    private Technician assignedTech;

    public static final String ROLETYPE_EMAIL = "Email";
    public static final String ROLETYPE_FILE = "File";
    public static final String ROLETYPE_PRINT = "Print";
    public static final String ROLETYPE_CUSTOM = "Custom";

    public Server(String inSID, String inLo, String inRo, String inFa) {

        serverID = inSID;
        location = inLo;
        if (inRo.equalsIgnoreCase(ROLETYPE_EMAIL)){
            role = inRo;
        } else if (inRo.equalsIgnoreCase(ROLETYPE_FILE)) {
            role = inRo;
        }else if (inRo.equalsIgnoreCase(ROLETYPE_PRINT)){
            role = inRo;
        } else {
            role = ROLETYPE_CUSTOM;
        }
        fault = inFa;

    }

    public String getServerID() {
        return serverID;
    }

    public String getLocation() {
        return location;
    }

    public String getRole() {
        return role;
    }

    public String getFault() {
        return fault;
    }

    public Technician getAssignedTech() {
        return assignedTech;
    }

    public void setAssignedTech(Technician inTech) {
        assignedTech = inTech;
    }

    @Override
    public String toString()
    {

        String r = "";

        r = r + "Server: " + serverID + "(Role: " + role + ")\n";
        r = r + "Fault: " + fault + " @ " + location+ "\n";

        if(assignedTech != null)
        {

            r = r + "Assigned to: " + assignedTech.toString();

        }
        else
        {
            r = r + "Assigned to: none assigned";
        }
        return r;
    }

}
