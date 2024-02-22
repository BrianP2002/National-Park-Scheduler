
/**
 * An object that used for store the ticket type and price of national parks.
 */
public class Ticket implements Comparable<Ticket> {
    private String title;
    private String description;
    private String cost;
    private String ID;

    /**
     * The Constructor of Ticket Class.
     * 
     * @param title the title of this ticket
     * @param desc  the description of this ticket
     * @param cost  the cost of this ticket
     */
    public Ticket(String title, String desc, String cost, String ID) {
	this.title = title;
	this.description = desc;
	this.cost = cost;
	this.ID = ID;
    }

    /**
     * Default Constructor of Ticket class. Nothing instantiated.
     */
    public Ticket() {

    }

    public String getTitle() {
	return this.title;
    }

    public void setTitle(String newTitle) {
	this.title = newTitle;
    }

    public String getDescription() {
	return this.description;
    }

    public void setDescription(String newDesc) {
	this.description = newDesc;
    }

    public String getCost() {
	return this.cost;
    }

    public void setCost(String newCost) {
	this.cost = newCost;
    }

    public String getID() {
	return this.ID;
    }

    public void setID(String newID) {
	this.ID = newID;
    }

    @Override
    public boolean equals(Object o) {
	if (o == this) {
	    return true;
	}

	if (!(o instanceof Ticket)) {
	    return false;
	}

	Ticket temp = (Ticket) o;

	return (this.cost == temp.getCost() && this.description.equals(temp.getDescription())
		&& this.title.equals(temp.getTitle()));
    }

    @Override
    public String toString() {
	return "Ticket Title: " + this.title + "\nTicket Cost: " + this.cost + "\nTicket Description: "
		+ this.description + "\nTicket ID: " + this.ID + "\n\n";
    }

    @Override
    public int compareTo(Ticket t) {
	return this.ID.compareTo(t.getID());
    }
}