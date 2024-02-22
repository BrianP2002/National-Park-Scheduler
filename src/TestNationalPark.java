
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNationalPark{

    @Test
    public void testSearchKeywords(){
        Park testPark = new Park();
        testPark.setDescription("There is a big waterfall! How amazing!");
        testPark.setName("test");
        testPark.setWeatherInfo("Sunny");
        assertEquals(testPark.containsInfo("waterfall"), true);
        assertEquals(testPark.containsInfo("Sunny"), true);
        assertEquals(testPark.containsInfo("amazing"), true);
        
        assertEquals(testPark.containsInfo("sunshine"), false);
    }

    @Test 
    public void testSearchState(){
        Park testPark = new Park();
        testPark.setState("WI, MN");
        assertEquals(testPark.containsState("WI"), true);
        assertEquals(testPark.containsState("MN"), true);

        
        assertEquals(testPark.containsState("CA"), false);
    }

    @Test
    public void testGetDescription(){
        Park testPark = new Park();
        testPark.setDescription("There is a big waterfall! How amazing!");
        assertEquals(testPark.getDescription(),"There is a big waterfall! How amazing!");
    }

    @Test
    public void testGetActivities(){
        Park testPark = new Park();
        testPark.addActivity(new Activity("ID1", "Activity 1"));
        testPark.addActivity(new Activity("ID2", "Activity 2"));
        assertEquals(testPark.printActivities(), "Activity Name: Activity 1\nActivity ID: ID1\nActivity Name: Activity 2\nActivity ID: ID2\n");
    }

    @Test
    public void testGetTickets(){
        Park testPark = new Park();
        testPark.addTickets(new Ticket("Entrance Fee", "This is a common entrance ticket", "60.0", "CommonEntrace-1"));
        testPark.addTickets(new Ticket("Entrance Pass", "This is a season pass", "150.0", "Pass-1"));
        String result = "Ticket Title: Entrance Fee\nTicket Cost: 60.0\nTicket Description: This is a common entrance ticket\nTicket ID: CommonEntrace-1\n\n" +
        "Ticket Title: Entrance Pass\nTicket Cost: 150.0\nTicket Description: This is a season pass\nTicket ID: Pass-1\n\n";
        assertEquals(testPark.printAllTickets(), result);
    }
}