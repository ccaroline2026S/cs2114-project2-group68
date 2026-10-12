import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

/** Tests the trip manager with the team's Destination and Activity classes. */
public class TripManagerTest
{
    @Test
    public void managesDestinationsByName()
    {
        TripManager trip = new TripManager();
        Destination paris = new Destination("Paris", "Eiffel Tower");
        assertTrue(trip.addDestination(paris));
        assertTrue(trip.searchDestination(new Destination("Paris", "")));

        assertTrue(trip.editDestination(paris,
            new Destination("Rome", "Colosseum")));
        assertFalse(trip.searchDestination(new Destination("Paris", "")));
        assertTrue(trip.searchDestination(new Destination("Rome", "")));
        assertEquals("Colosseum", paris.getDescription());

        assertTrue(trip.removeDestination(new Destination("Rome", "")));
        assertFalse(trip.searchDestination(new Destination("Rome", "")));
    }

    @Test
    public void destinationMethodsHandleNullAndMissingItems()
    {
        TripManager trip = new TripManager();
        Destination missing = new Destination("Missing", "");
        assertFalse(trip.addDestination(null));
        assertFalse(trip.searchDestination(null));
        assertFalse(trip.removeDestination(missing));
        assertFalse(trip.editDestination(missing,
            new Destination("Rome", "")));
    }


    @Test
    public void managesActivitiesByName()
    {
        TripManager trip = new TripManager();
        Activity hike = new Activity("Hiking", "Mountain trail");
        assertTrue(trip.addActivity(hike));
        assertTrue(trip.searchActivity(new Activity("Hiking", "")));

        assertTrue(trip.editActivity(hike,
            new Activity("Kayaking", "River")));
        assertFalse(trip.searchActivity(new Activity("Hiking", "")));
        assertTrue(trip.searchActivity(new Activity("Kayaking", "")));
        assertEquals("River", hike.getDescription());

        assertTrue(trip.removeActivity(new Activity("Kayaking", "")));
        assertFalse(trip.searchActivity(new Activity("Kayaking", "")));
    }

    @Test
    public void activityMethodsHandleNullAndMissingItems()
    {
        TripManager trip = new TripManager();
        Activity missing = new Activity("Missing", "");
        assertFalse(trip.addActivity(null));
        assertFalse(trip.searchActivity(null));
        assertFalse(trip.removeActivity(missing));
        assertFalse(trip.editActivity(missing,
            new Activity("Kayaking", "")));
    }


    @Test
    public void displayTripHandlesEmptyAndPopulatedTrips()
    {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        try
        {
            TripManager trip = new TripManager();
            trip.displayTrip();
            assertTrue(output.toString().contains("No destinations added yet."));
            assertTrue(output.toString().contains("No activities added yet."));

            output.reset();
            trip.addDestination(new Destination("Paris", "Trip"));
            trip.addActivity(new Activity("Hiking", "Trip"));
            trip.displayTrip();
            assertTrue(output.toString().contains("Paris: Trip"));
            assertTrue(output.toString().contains("Hiking"));
        }
        finally
        {
            System.setOut(oldOut);
        }
    }
}
