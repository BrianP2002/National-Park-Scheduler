// --== CS400 File Header Information ==--
// Name: Lin Ha
// Email: lha2@wisc.edu
// Notes to Grader:

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


@SuppressWarnings("unused")
public class BackEnd {
    // the fornt end linked with this back end
    private FrontEnd frontEnd;

    // the data structure that used for storing data
    private HashTableMap<String, Park> Parks;
    private Park crtPark;

    // the class that used to store the travel plan.
    private TravelPlan tp;

    // the Enum that used for storing the status of the system
    protected enum SystemStatus {
		BEGIN, RUNNING, TERMINATED
    }

    // the Enum that used for storing the status of loading data
    protected enum DataStatus {
		UNITIALIZED, LOADING_SUCCESS, LOADING_FAIL
    }

    protected enum FileStatus {
		WRITING, SAVED
    }

    protected DataStatus dataStatus;
    protected SystemStatus systemStatus;
    protected FileStatus fileStatus;

    /**
     * Initialize the back end of the program and load in the data.
     * 
     * @param givenFE the given front end that this back end needs to bind with
     */
    public BackEnd(FrontEnd givenFE) {
		this.frontEnd = givenFE;
		this.tp = new TravelPlan();
		this.systemStatus = SystemStatus.BEGIN;
		this.dataStatus = DataStatus.UNITIALIZED;
		this.fileStatus = FileStatus.SAVED;
		this.Parks = new HashTableMap<String, Park>();
		for (int i = 0; (this.dataStatus != DataStatus.LOADING_SUCCESS) && i < 10; ++i) {
			loadData();
		}
		if (this.dataStatus == DataStatus.LOADING_SUCCESS) {
			this.systemStatus = SystemStatus.RUNNING;
			this.frontEnd.printSystemMessage(Text.LOADING_SUCCESS_WORDS);
			this.frontEnd.printSystemMessage(Text.WELCOME_WORDS);
		} else {
			this.frontEnd.printSystemMessage(Text.LOADING_FAIL_WORDS);
			this.systemStatus = SystemStatus.TERMINATED;
		}
    }

    /**
     * Load in the data into their corresponding hash table. If there is any
     * exception caught, then terminates the program.F
     */
    private void loadData() {
	try {
	    this.loadParks();
	    this.dataStatus = DataStatus.LOADING_SUCCESS;
	} catch (Exception e) {
	    // e.printStackTrace();
	    this.dataStatus = DataStatus.LOADING_FAIL;
	}
    }

    /**
     * Load in the basic information of national parks.
     * 
     * @throws JsonIOException          This exception is raised when Gson was
     *                                  unable to read an input stream or write to
     *                                  one
     * @throws JsonSyntaxException      This exception is raised when Gson attempts
     *                                  to read (or write) a malformed JSON element
     * @throws FileNotFoundException    If the file not existed
     * @throws NumberFormatException    If the given data file is not
     * @throws IllegalArgumentException If the given data mismatches with the
     *                                  expected data format
     */
    private void loadParks() throws JsonIOException, JsonSyntaxException, FileNotFoundException, NumberFormatException,
	    IllegalArgumentException {
	JsonObject object = JsonParser.parseReader(new FileReader("../Data/NationalParkData.json")).getAsJsonObject();
	int num = Integer.parseInt(object.get("total").getAsString());
	JsonArray data = object.get("data").getAsJsonArray();
	for (int i = 0; i < num; ++i) {
	    JsonObject newData = data.get(i).getAsJsonObject();
	    Park newPark = new Park();
	    newPark.setName(newData.get("name").getAsString());
	    newPark.setFullName(newData.get("fullName").getAsString());
	    newPark.setDescription(newData.get("description").getAsString());
	    newPark.setDesgination(newData.get("designation").getAsString());
	    newPark.setDirectionInfo(newData.get("directionsInfo").getAsString());
	    newPark.setID(newData.get("id").getAsString());
	    newPark.setParkCode(newData.get("parkCode").getAsString());
	    newPark.setState(newData.get("states").getAsString());
	    newPark.setWeatherInfo(newData.get("weatherInfo").getAsString());
	    newPark.setWebsite(newData.get("url").getAsString());
	    newPark.setLatLong(newData.get("latLong").getAsString());
	    JsonArray entranceFees = newData.get("entranceFees").getAsJsonArray();
	    JsonArray entrancePasses = newData.get("entrancePasses").getAsJsonArray();
	    JsonArray activities = newData.get("activities").getAsJsonArray();
	    // Loading tickets of this national park
	    String title, desc, cost;
	    String[] Ohours;
	    int feeCounter = 0, passCounter = 0;
	    JsonObject crt;
	    for (int j = 0; j < entranceFees.size(); ++j) {
		crt = entranceFees.get(j).getAsJsonObject();
		title = crt.get("title").getAsString();
		desc = crt.get("description").getAsString();
		cost = crt.get("cost").getAsString();
		Ticket newTicket = new Ticket(title, desc, cost, "CommonEntrace-" + String.valueOf(++feeCounter));
		newPark.addTickets(newTicket);
	    }
	    for (int j = 0; j < entrancePasses.size(); ++j) {
		crt = entrancePasses.get(j).getAsJsonObject();
		title = crt.get("title").getAsString();
		desc = crt.get("description").getAsString();
		cost = crt.get("cost").getAsString();
		Ticket newTicket = new Ticket(title, desc, cost, "Pass-"+String.valueOf(++passCounter));
		newPark.addTickets(newTicket);
	    }

	    // Loading operating hours

	    String name;
	    if (newData.get("operatingHours").getAsJsonArray().size() != 0) {
		JsonObject standardHour = newData.get("operatingHours").getAsJsonArray().get(0).getAsJsonObject();
		JsonArray exceptionHours = standardHour.get("exceptions").getAsJsonArray();
		OperatingHour newHour;
		newHour = new OperatingHour(standardHour.get("name").getAsString() + "(Standard Hour)",
			this.loadHoursStringHelper(standardHour.get("standardHours").getAsJsonObject()), "always",
			"always");
		newPark.addHour(newHour);
		for (int j = 0; j < exceptionHours.size(); ++j) {
		    crt = exceptionHours.get(j).getAsJsonObject();
		    name = crt.get("name").getAsString();
		    OperatingHour _newHour = new OperatingHour(name,
			    this.loadHoursStringHelper(crt.get("exceptionHours").getAsJsonObject()),
			    crt.get("startDate").getAsString(), crt.get("endDate").getAsString());
		    newPark.addHour(_newHour);
		}
	    }
	    // Loading Activities
	    String ID;
	    for (int j = 0; j < activities.size(); ++j) {
		crt = activities.get(j).getAsJsonObject();
		name = crt.get("name").getAsString();
		ID = crt.get("id").getAsString();
		Activity newActivity = new Activity(ID, name);
		newPark.addActivity(newActivity);
	    }

	    // add this new park into the collection of parks
	    this.Parks.put(newPark.getParkCode(), newPark);
	}
    }

    /**
     * This is a private helper method that can extract the operating hour
     * information from a JsonObject.
     * 
     * @param hour the given JsonObject
     * @return a String array that has length of 7 with the information of operating
     *         hours of each day in the week, starting form Sunday.
     */
    private String[] loadHoursStringHelper(JsonObject hour) {
		String[] result = new String[7];
		result[0] = hour.get("sunday") == null ? "closed" : hour.get("sunday").getAsString();
		result[1] = hour.get("monday") == null ? "closed" : hour.get("monday").getAsString();
		result[2] = hour.get("tuesday") == null ? "closed" : hour.get("tuesday").getAsString();
		result[3] = hour.get("wednesday") == null ? "closed" : hour.get("wednesday").getAsString();
		result[4] = hour.get("thursday") == null ? "closed" : hour.get("thursday").getAsString();
		result[5] = hour.get("friday") == null ? "closed" : hour.get("friday").getAsString();
		result[6] = hour.get("saturday") == null ? "closed" : hour.get("saturday").getAsString();
		return result;
    }

    protected void processInput(String readIn) {
	String[] data = readIn.split(" ");
	switch (data.length) {
	case 1:
	    switch (data[0]) {
	    case "end":
		if (this.fileStatus == FileStatus.WRITING) {
		    if (this.writeFile("temp")) {
			this.fileStatus = FileStatus.SAVED;
			this.frontEnd.printSystemMessage(Text.SAVING_SUCESS);
		    }
		}
		this.systemStatus = SystemStatus.TERMINATED;
		break;
	    case "help":
		this.frontEnd.printSystemMessage(Text.HELP_LIST);
		break;
	    case "all":
		this.printAllParks();
		break;
	    case "clear":
		this.tp = new TravelPlan();
		this.fileStatus = FileStatus.WRITING;
		break;
	    case "print":
		this.frontEnd.printSystemMessage(this.tp.toString());
		break;
	    default:
		this.frontEnd.printSystemMessage(Text.UNKNOWN_COMMAND);
		break;
	    }
	    break;
	case 2:
	    switch (data[0]) {
	    case "search":
			if(data[1].length() == 2){
				this.frontEnd.printSystemMessage(this.searchStates(data[1]));
				break;
			}
			this.frontEnd.printSystemMessage(this.searchPark(data[1]));
			break;
	    case "select":
			if (!this.Parks.containsKey(data[1])) {
				this.frontEnd.printSystemMessage(Text.UNKNOWN_PARK);
				break;
			}
		this.crtPark = this.Parks.get(data[1]);
		this.frontEnd.printSystemMessage(Text.DETAIL_INFO);
		break;
	    case "detail":
		if (crtPark == null) {
		    this.frontEnd.printSystemMessage("Please select a park first!\n");
		    break;
		}
		switch (data[1]) {
		case "fullName":
		case "FullName":
		    this.frontEnd.printSystemMessage(crtPark.getFullName());
		    break;
		case "id":
		case "ID":
		    this.frontEnd.printSystemMessage(crtPark.getID());
		    break;
		case "States":
		case "State":
		    this.frontEnd.printSystemMessage(crtPark.getState());
		    break;
		case "Website":
		case "website":
		case "url":
		    this.frontEnd.printSystemMessage(crtPark.getWebsite());
		    break;
		case "Position":
		case "postion":
		    this.frontEnd.printSystemMessage(crtPark.getLatLong());
		    break;
		case "Activity":
		case "Activities":
		    this.frontEnd.printSystemMessage(crtPark.printActivities());
		    break;
		case "Tickets":
		case "ticket":
		case "Ticket":
		case "tickets":
		    this.frontEnd.printSystemMessage(crtPark.printAllTickets());
		    break;
		case "OperatingHours":
		case "operatinghours":
		case "operatingHours":
		case "Operatinghours":
		    this.frontEnd.printSystemMessage(crtPark.printAllOpenInterval());
		    break;
		case "Desgination":
		case "desgination":
		    this.frontEnd.printSystemMessage(crtPark.getDesgination());
		    break;
		case "DirectionInfo":
		case "Directioninfo":
		case "directionInfo":
		case "directioninfo":
		    this.frontEnd.printSystemMessage(crtPark.getDirectionInfo());
		    break;
		case "WeatherInfo":
		case "Weatherinfo":
		case "weatherInfo":
		case "weatherinfo":
		    this.frontEnd.printSystemMessage(crtPark.getWeatherInfo());
		    break;
		case "description":
		case "Description":
		    this.frontEnd.printSystemMessage(crtPark.getDescription());
		    break;
		default:
		    this.frontEnd.printSystemMessage(Text.UNKNOWN_COMMAND);
		    break;
		}
		break;
	    case "add":
		if (!this.Parks.containsKey(data[1])) {
		    this.frontEnd.printSystemMessage(Text.UNKNOWN_PARK);
		    break;
		}
		if (!this.tp.addPark(this.Parks.get(data[1]))) {
		    this.frontEnd.printSystemMessage(Text.ALREADY_IN_PARK);
		    break;
		}
		this.fileStatus = FileStatus.WRITING;
		break;
	    case "del":
		if (!this.Parks.containsKey(data[1])) {
		    this.frontEnd.printSystemMessage(Text.UNKNOWN_PARK);
		}
		if (!this.tp.removePark(data[1])) {
		    this.frontEnd.printSystemMessage(Text.UNKNOWN_PARK_RMV);
		    break;
		}
		this.fileStatus = FileStatus.WRITING;
		break;
	    case "save":
		if (this.writeFile(data[1])) {
		    this.fileStatus = FileStatus.SAVED;
		    this.frontEnd.printSystemMessage(Text.SAVING_SUCESS);
		} else {
		    this.frontEnd.printSystemMessage(Text.SAVING_FAILED);
		}
		break;
	    default:
		this.frontEnd.printSystemMessage(Text.UNKNOWN_COMMAND);
		break;
	    }
	    break;
	case 3:
	    switch (data[0]) {
	    case "add":
		if (!this.Parks.containsKey(data[1]) || this.Parks.get(data[1]).getActivity(data[2]) == null
			&& this.Parks.get(data[1]).getTicket(data[2]) == null) {
		    System.out.println(data[2]);
		    this.frontEnd.printSystemMessage("The given combination is not existed in the data base.\n");
		    break;
		}
		if (this.Parks.get(data[1]).getActivity(data[2]) != null) {
		    if (!this.tp.addActivity(this.Parks.get(data[1]), this.Parks.get(data[1]).getActivity(data[2]))) {
			this.frontEnd.printSystemMessage(Text.ALREADY_IN_Activity);
			break;
		    }
		} else {
		    if (!this.tp.addTicket(this.Parks.get(data[1]), this.Parks.get(data[1]).getTicket(data[2]))) {
			this.frontEnd.printSystemMessage(Text.ALREADY_IN_TICKET);
			break;
		    }
		}
		this.fileStatus = FileStatus.WRITING;
		break;
	    case "del":
		if (!this.Parks.containsKey(data[1]) || this.Parks.get(data[1]).getActivity(data[2]) == null
			&& this.Parks.get(data[1]).getTicket(data[2]) == null) {
		    this.frontEnd.printSystemMessage("The given combination is not existed in the data base.\n");
		    break;
		}
		if (this.Parks.get(data[1]).getActivity(data[2]) != null) {
		    if (!this.tp.removeActivity(data[1], data[2])) {
			this.frontEnd.printSystemMessage(Text.UNKNOWN_ACTIVITY_RMV);
			break;
		    }
		}
		else {
		    if (!this.tp.removeTicket(data[1], data[2])) {
			this.frontEnd.printSystemMessage(Text.UNKNOWN_TICKET_RMV);
			break;
		    }
		}
		this.fileStatus = FileStatus.WRITING;
		break;
	    default:
		this.frontEnd.printSystemMessage(Text.UNKNOWN_COMMAND);
		break;
	    }
	    break;
	default:
	    this.frontEnd.printSystemMessage(Text.UNKNOWN_COMMAND);
	    break;
	}
    }

    protected String searchPark(String keyword) {
		String str = "";
		for (Park crt : this.Parks.valueSet()) {
			if (crt.containsInfo(keyword)) {
				str += crt.getParkCode() + "(abbr), " + crt.getFullName() + ", located at: " + crt.getState() + "\n";
			}
		}
		if (str.equals("")) {
			str = "No related park founded.\n";
		}
		return str;
    }

	protected String searchStates(String state){
		String str = "";
		for(Park crt: this.Parks.valueSet()){
			if(crt.containsState(state)){
				str += crt.getParkCode() + "(abbr), " + crt.getFullName() + ", located at: " + crt.getState() + "\n";
			}
		}
		if (str.equals("")) {
			str = "No related park founded.\n";
		}
		return str;
	}

    protected boolean writeFile(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("../" + fileName + ".txt"));
			writer.write(this.tp.toString());
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
    }

    protected void printAllParks() {
		ArrayList<Park> temp = this.Parks.valueSet();
		for (Park crt : temp) {
			this.frontEnd.printSystemMessage(
				crt.getParkCode() + "(abbr), " + crt.getFullName() + ", located at: " + crt.getState() + "\n");
		}
    }
}
