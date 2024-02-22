// --== CS400 File Header Information ==--
// Name: Lin Ha
// Email: lha2@wisc.edu
// Notes to Grader:

import java.util.*;

@SuppressWarnings("unused")
public class Park {
    private String name;
    private String fullName;
    private String ID;
    private String description;
    private String desgination;
    private String directionInfo;
    private String parkCode;
    private String states;
    private String weatherInfo;
    private String website;
    private String latLong;
    private HashTableMap<String, Activity> activities;
    private ArrayList<Ticket> tickets;
    private HashTableMap<String, OperatingHour> hours;
    
    public Park() {
	this.activities = new HashTableMap<String, Activity>();
	this.tickets = new ArrayList<Ticket>();
	this.hours = new HashTableMap<String, OperatingHour>();
    }

    public String getName() {
	return this.name;
    }

    public void setName(String newName) {
	this.name = newName;
    }

    public String getFullName() {
	return this.fullName;
    }

    public void setFullName(String newFullName) {
	this.fullName = newFullName;
    }

    public String getID() {
	return this.ID;
    }

    public void setID(String newID) {
	this.ID = newID;
    }

    public String getDescription() {
	return this.description;
    }

    public void setDescription(String newDesc) {
	this.description = newDesc;
    }

    public String getDesgination() {
	return this.desgination;
    }

    public void setDesgination(String newDesg) {
	this.desgination = newDesg;
    }

    public String getDirectionInfo() {
	return this.directionInfo;
    }

    public void setDirectionInfo(String newDir) {
	this.directionInfo = newDir;
    }

    public String getParkCode() {
	return this.parkCode;
    }

    public void setParkCode(String newCode) {
	this.parkCode = newCode;
    }

    public String getState() {
	return this.states;
    }

    public void setState(String newStates) {
	this.states = newStates;
    }

    public String getWeatherInfo() {
	return this.weatherInfo;
    }

    public void setWeatherInfo(String newWeather) {
	this.weatherInfo = newWeather;
    }

    public String getWebsite() {
	return this.website;
    }

    public void setWebsite(String newWebsite) {
	this.website = newWebsite;
    }
    
    public String getLatLong() {
	return this.latLong;
    }
    public void setLatLong(String newLatLong) {
	this.latLong = newLatLong;
    }

    /**
     * Add the given activity into the set of activities.
     * 
     * @param newActivity the given activity
     * @return true if added successfully, false if the newActivity is null or it's
     *         already in the collection
     */
    public boolean addActivity(Activity newActivity) {
	if (newActivity == null) {
	    return false;
	}
	return this.activities.put(newActivity.getID(), newActivity);
    }

    /**
     * Remove the activity that has the given id from the collection of activities.
     * 
     * @param id the given id
     * @return true if removed successfully, false if the id is null or it's not in
     *         the collection
     */
    public Activity removeActivity(String id) {
	if (id == null) {
	    return null;
	}
	return this.activities.remove(id);
    }

    /**
     * Return all the activities of this national park.
     * @return a string that contains all the information of activies of this park. 
     */
    public String printActivities() {
        ArrayList<Activity> temp = new ArrayList<Activity>();
        temp = this.activities.valueSet();
        Collections.sort(temp);
        String str = "";
        for (Activity crt : temp) {
            str += crt.toString();
        }
        if (temp.size() == 0) {
            str += "There is no activity for this park";
        }
        return str;
    }

    public Activity getActivity(String givenID) {
        try {
            return this.activities.get(givenID);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Add the given operating hours into the set of operating hours.
     * 
     * @param newActivity the given operating hour
     * @return true if added successfully, false if the newHour is null or it's
     *         already in the collection
     */
    public boolean addHour(OperatingHour newHour) {
	if (newHour == null) {
	    return false;
	}
	return this.hours.put(newHour.getName(), newHour);
    }

    /**
     * Remove the operating hour that has the given name from the collection of
     * operating hours.
     * 
     * @param name the given name
     * @return true if removed successfully, false if the name is null or it's not
     *         in the collection
     */
    public OperatingHour removeHour(String name) {
	if (name == null) {
	    return null;
	}
	return this.hours.remove(name);
    }

    /**
     * Return all the open intervals of this national park.
     * @return a String that contains all the information of open intervals of this park.
     */
    public String printAllOpenInterval() {
        String str = "";
        ArrayList<OperatingHour> temp = this.hours.valueSet();
        if (temp.size() == 0) {
            str = "There is no operting hours' data for this park.\n";
        }
        Collections.sort(temp);
        for (OperatingHour crt : temp) {
            str += crt.toString();
        }
        return str;
    }

    /**
     * Add the given ticket into the collection of tickets.
     * 
     * @param newTicket the given ticket
     * @return true if the new ticket is successfully added, false if newTicket is
     *         null
     */
    public boolean addTickets(Ticket newTicket) {
        if (newTicket == null) {
            return false;
        }
        this.tickets.add(newTicket);
        return true;
    }
    
    /**
     * Return the Ticket object with the given ID. 
     * @param title the given ID
     * @return a Ticket object, null if there is no such ticket
     */
    public Ticket getTicket(String ID) {
        for(int i = 0; i < this.tickets.size(); ++i) {
            if(this.tickets.get(i).getID().equals(ID)) {
            return this.tickets.get(i);
            }
        }
        return null;
    }

    /**
     * Remove the ticket with the given ID from the collection of tickets.
     * 
     * @param ID the given ID
     * @return true if moved successfully, false if ID is null or does not exist
     *         in the collection of tickets.
     */
    public boolean removeTicket(String ID) {
        if (ID == null) {
            return false;
        }
        for (int i = 0; i < this.tickets.size(); ++i) {
            if (this.tickets.get(i).getTitle().equals(ID)) {
            this.tickets.remove(i);
            return true;
            }
        }
        return false;
    }

    /**
     * Return all the tickets of this national park.
     * @return a string that contains all the information of tickets within this park.
     */
    public String printAllTickets() {
        String str = "";
        Collections.sort(this.tickets);
        for (Ticket crt : this.tickets) {
            str += crt.toString();
        }
        if(this.tickets.size() == 0){
            str = "There is no ticket information for this park.\n";
        }
        return str;
    }

    public boolean containsInfo(String keyword){
        if (this.name != null && this.name.contains(keyword)) 
            return true;
        if(this.fullName != null && this.fullName.contains(keyword)) 
            return true;
        if(this.description != null && this.description.contains(keyword))
            return true;
        if(this.parkCode != null && this.parkCode.contains(keyword))
            return true;
        if(this.ID != null && this.ID.contains(keyword))
            return true;
        if(this.weatherInfo != null && this.weatherInfo.contains(keyword))
            return true;
        return false;
    }


    public boolean containsState(String state){
        if(this.states.contains(state)){
            return true;
        }
        return false;
    }

}
