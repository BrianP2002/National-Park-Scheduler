/**
 * An object that used for store the activities of the national parks.
 */
public class Activity implements Comparable<Activity> {
    private String ID;
    private String name;

    /**
     * The constructor of Activity class.
     * 
     * @param ID   the ID of this activity
     * @param name the name of this activity
     */
    public Activity(String ID, String name) {
	this.ID = ID;
	this.name = name;
    }

    /**
     * Default constructor of Activity class. Nothing instantiated.
     */
    public Activity() {
    }

    public String getID() {
	return this.ID;
    }

    public void setID(String newID) {
	this.ID = newID;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String newName) {
	this.name = newName;
    }

    public boolean checkID(String givenID) {
	return this.ID.equals(givenID);
    }

    @Override
    public boolean equals(Object o) {
	if (o == this) {
	    return true;
	}

	if (!(o instanceof Activity)) {
	    return false;
	}

	return this.ID.equals(((Activity) o).getID());
    }

    @Override
    public String toString() {
	return "Activity Name: " + this.name + "\nActivity ID: " + this.ID + "\n";
    }

    @Override
    public int compareTo(Activity a) {
	return this.name.compareTo(a.getName());
    }
}