/**
 * An object that used for store the operating hours of the national parks.
 */
public class OperatingHour implements Comparable<OperatingHour> {
    private String name;
    private String[] openInterval;
    private String beginDate;
    private String endDate;

    /**
     * The constructor of OperatingHour class.
     * 
     * @param name         the name of the operating hour.
     * @param openInterval a string array that stores the information of the
     *                     operating interval on each day of the week
     * @throws IllegalArgumentException if the openInterval has length not 7.
     */
    public OperatingHour(String name, String[] openInterval, String beginDate, String endDate) throws IllegalArgumentException {
	if (openInterval.length != 7) {
	    throw new IllegalArgumentException(
		    "The given new open interval string[] does not fit the requirement of format");
	}
	this.name = name;
	this.openInterval = openInterval;
	this.beginDate = beginDate;
	this.endDate = endDate;
    }

    /**
     * Default Constructor of OperatingHour class. Nothing instantiated.
     */
    public OperatingHour() {

    }

    public String getName() {
	return this.name;
    }

    public void SetName(String newName) {
	this.name = newName;
    }

    public String[] getOpenInterval() {
	return this.openInterval;
    }

    /**
     * Passing a String array with exactly length of 7, representing the open/close
     * status of a specific day in the week, starting from Sunday.
     * 
     * @param newOI the given String array
     * @throws IllegalArgumentException if the length of the given String array is
     *                                  not 7
     */
    public void setOpenInterval(String[] newOI) throws IllegalArgumentException {
	if (newOI.length != 7) {
	    throw new IllegalArgumentException(
		    "The given new open interval string[] does not fit the requirement of format");
	}
	this.openInterval = newOI;
    }
    
    public String getBeginDate() {
	return this.beginDate;
    }
    
    public void setBeginDate(String newDate) {
	this.beginDate = newDate;
    }
    
    public String getEndDate() {
	return this.endDate;
    }
    
    public void setEndDate(String newDate) {
	this.endDate = newDate;
    }
    
    @Override
    public boolean equals(Object o) {
	if (o == this) {
	    return true;
	}

	if (!(o instanceof OperatingHour)) {
	    return false;
	}

	OperatingHour temp = (OperatingHour) o;
	return (this.name.equals(temp.getName()));
    }

    @Override
    public String toString() {
	return "Name:  " + this.name + "\nOpen Interval: \n" + "Sunday:    " + this.openInterval[0] + "\n"
		+ "Monday:    " + this.openInterval[1] + "\n" + "Tuesday:   " + this.openInterval[2] + "\n"
		+ "Wednesday: " + this.openInterval[3] + "\n" + "Thursday:  " + this.openInterval[4] + "\n"
		+ "Friday:    " + this.openInterval[5] + "\n" + "Saturday:  " + this.openInterval[6] + "\n";
    }

    @Override
    public int compareTo(OperatingHour o) {
	return this.name.compareTo(o.getName());
    }
}