// --== CS400 File Header Information ==--
// Name: Lin Ha
// Email: lha2@wisc.edu
// Notes to Grader:

public class Text {
    protected static final String LOADING_WORDS = "Please wait, Loading...\n";
    protected static final String LOADING_FAIL_WORDS = "Loading data failed! Please check the data and try again!\n";
    protected static final String LOADING_SUCCESS_WORDS = "Loading data successfully!\n";
    protected static final String WELCOME_WORDS = "Welcome to the Travel Agent of National Parks.\n"
	    + "You can search and recieve the basic information of 400+ national parks located in USA\n"
	    + "with their open/close time, ticket price, events, and etc. Type the command \"help\" to\n"
	    + "check the whole command list.\n";
    protected static final String HELP_LIST = "You can use the following command for your search:\n"
	    + "all				   	-- get the list of all national parks and it's state code.\n"
	    + "search [keywords]                  	-- recieve a list of related national parks' names. Please search one keyword per time.\n"
	    + "                                            If you want to search about national parks within a state, use the state code instead of full name.\n"
	    + "select [park's abbr name]          	-- select a specific park, the terminal will give you a list of options such that you can check.\n"
	    + "                                            the specific information by typing \"select [option]\".\n"
	    + "detail [option]                    	-- shows the information under this option.\n"
	    + "print                              	-- print out the current travel plan.\n"
	    + "clear                              	-- clear the current travel plan.\n"
	    + "add [park's abbr name]                  -- add the given park to your travel plan.\n"
	    + "add [park's abbr name] [event's id]     -- add the given event of the given park to your travel plan.\n"
	    + "add [park's abbr name] [ticket's name]  -- add the given ticket of the given park to your travel plan.\n"
	    + "del [park's abbr name]                  -- delete the given park from your travel plan.\n"
	    + "del [park's abbr name] [event's id]     -- delete the given activity from of the given park from your travel plan.\n"
	    + "del [park's abbr name] [ticket's name]  -- delete the given ticket from of the given park from your travel plan.\n"
	    + "save [file's name]                      -- save the current travel plan to the given txt file.\n"
	    + "end				        -- End the apaplication.\n";
    protected static final String DETAIL_INFO = "You can further know about the following information by using the \"detail\" command:\n"
    	+ "FullName, ID, States, Website, Position(in coordinates), Activities, Tickets, OperatingHours, Desgination, DirectionInfo, WeatherInfo, Description";
    protected static final String UNKNOWN_COMMAND = "Unknown command, please try again!\n";
    protected static final String UNKNOWN_PARK = "The given park is not in the data base!\n";
    
    protected static final String SAVING_CHECK = "You are not saving your plan. Do you want to save the plan? (y/n):";
    protected static final String SAVING_SUCESS = "Your plan is saved successfully!\n";
    protected static final String SAVING_FAILED = "Uh-oh, met some problems when saving data.\n";
    
    protected static final String UNKNOWN_PARK_ADD = "The park you want to add is not existed in the data base.\n";
    protected static final String UNKNOWN_ACTIVITY_ADD = "The activity you want to add is not existed in the data base.\n";
    protected static final String UNKNOWN_TICKET_ADD = "The ticket you want to add is not existed in the data base.\n";

    protected static final String UNKNOWN_PARK_RMV = "The park you want to remove is not existed in the travel plan.\n";
    protected static final String UNKNOWN_ACTIVITY_RMV = "The activity you want to add is not existed in the travel plan.\n";
    protected static final String UNKNOWN_TICKET_RMV = "The ticket you want to add is not existed in the travel plan.\n";
    
    protected static final String ALREADY_IN_PARK = "The park you want to add is already in the travel plan.\n";
    protected static final String ALREADY_IN_Activity = "The activity you want to add is already in the travel plan.\n";
    protected static final String ALREADY_IN_TICKET = "The ticket you want to add is already in the travel plan.\n";
   
    protected static final String END_WORDS = "Thanks for using the system! See you next time.\n";
}
