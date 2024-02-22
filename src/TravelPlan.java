@SuppressWarnings("unused")
public class TravelPlan {

    private class _Park {
	_Park(String name) {
	    this.name = name;
	    this.activities = new HashTableMap<String, _Activity>();
	    this.tickets = new HashTableMap<String, _Ticket>();
	}

	String name;
	HashTableMap<String, _Activity> activities;
	HashTableMap<String, _Ticket> tickets;
    }

    private class _Activity {
	String name;
	String ID;

	_Activity(String name, String ID) {
	    this.name = name;
	    this.ID = ID;
	}
    }

    private class _Ticket {
	String title, ID;
	Double cost;

	_Ticket(String title, String cost, String ID) throws NumberFormatException {
	    this.title = title;
	    this.cost = Double.parseDouble(cost);
	    this.ID = ID;
	}
    }

    private HashTableMap<String, _Park> parks;

    public TravelPlan() {
	this.parks = new HashTableMap<String, _Park>();
    }

    public boolean addPark(Park newPark) {
	if (parks.containsKey(newPark.getParkCode())) {
	    return false;
	}
	parks.put(newPark.getParkCode(), new _Park(newPark.getName()));
	return true;
    }

    public boolean removePark(String parkCode) {
	return (this.parks.remove(parkCode) != null);
    }

    public boolean addActivity(Park newPark, Activity newActivity) {
	if (!parks.containsKey(newPark.getParkCode())) {
	    _Park _newPark = new _Park(newPark.getName());
	    _newPark.activities.put(newActivity.getID(), new _Activity(newActivity.getName(), newActivity.getID()));
	    parks.put(newPark.getParkCode(), _newPark);
	    return true;
	}
	if (parks.get(newPark.getParkCode()).activities.containsKey(newActivity.getID())) {
	    return false;
	}
	parks.get(newPark.getParkCode()).activities.put(newActivity.getID(),
		new _Activity(newActivity.getName(), newActivity.getID()));
	return true;
    }

    public boolean removeActivity(String parkCode, String activityID) {
	return (this.parks.containsKey(parkCode) && this.parks.get(parkCode).activities.remove(activityID) != null);
    }

    public boolean addTicket(Park newPark, Ticket newTicket) {
	if (!parks.containsKey(newPark.getParkCode())) {
	    _Park _newPark = new _Park(newPark.getName());
	    try {
		_newPark.tickets.put(newTicket.getID(), new _Ticket(newTicket.getTitle(), newTicket.getCost(), newTicket.getID()));
		this.parks.put(newPark.getParkCode(), _newPark);
		return true;
	    } catch (Exception e) {
		return false;
	    }
	}
	if (parks.get(newPark.getParkCode()).tickets.containsKey(newTicket.getID())) {
	    return false;
	}
	parks.get(newPark.getParkCode()).tickets.put(newTicket.getID(),
		new _Ticket(newTicket.getTitle(), newTicket.getCost(), newTicket.getID()));
	return true;
    }
    
    public boolean removeTicket(String parkCode, String ID) {
	return (this.parks.containsKey(parkCode) && this.parks.get(parkCode).tickets.remove(ID) != null);
    }

    @Override
    public String toString() {
	if (this.parks.size() == 0) {
	    return "There is no travel plan made yet.\n";
	}
	String str = "";
	Double totalCost = 0.0;
	for (_Park crt : this.parks.valueSet()) {
	    str += crt.name + "\n";
	    if (crt.activities.size() != 0) {
		str += "Activities:\n";
	    }
	    for (_Activity _crt : crt.activities.valueSet()) {
		str += "\t" + _crt.name + ": " + _crt.ID + "\n";
	    }
	    if (crt.tickets.size() != 0) {
		str += "Tickets:\n";
	    }
	    for (_Ticket _crt : crt.tickets.valueSet()) {
		str += "\t" + _crt.title + ": " + _crt.cost + "\n";
		totalCost += _crt.cost;
	    }
	}
	if (totalCost != 0.0) {
	    str += "total cost of tickets: " + totalCost + "\n";
	}
	else {
	    str += "the travel plan does not cost money for tickets\n";
	}
	return str;
    }
}