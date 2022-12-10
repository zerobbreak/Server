
public class Technician {

    private final String techID;
    private final String name;
    private final int experience;
    private final String roleSpeciality;

    public Technician(String inTID, String inN, int inE, String inR) {

        techID = inTID;
        name = inN;
        experience = inE;
        roleSpeciality = inR;

    }

    public String getTechID() {
        return techID;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public String getRoleSpeciality() {
        return roleSpeciality;
    }

    @Override
    public String toString() {
        return name + ", " +
                techID + ", " +
                experience + " year(s)" +
                "[" + roleSpeciality + "]";
    }
}
