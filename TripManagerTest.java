import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

/** Tests adding, finding, changing, removing, and displaying trip items. */
public class TripManagerTest
{
    // Check that a destination can be managed from start to finish.
    @Test
    public void addsSearchesEditsAndRemovesDestination()
    {
        TripManager trip = new TripManager();
        assertTrue(trip.addDestination("Paris", "Eiffel Tower"));
        assertEquals("Eiffel Tower",
            trip.searchDestination("Paris").getDescription());
        assertTrue(trip.editDestination("Paris", "Rome", "Colosseum"));
        assertNull(trip.searchDestination("Paris"));
        assertEquals("Colosseum",
            trip.searchDestination("Rome").getDescription());
        assertTrue(trip.removeDestination("Rome"));
        assertNull(trip.searchDestination("Rome"));
    }

    // Missing items and invalid names should be handled without exceptions.
    @Test
    public void destinationMethodsHandleInvalidInput()
    {
        TripManager trip = new TripManager();
        assertFalse(trip.addDestination(" ", "Description"));
        assertFalse(trip.removeDestination("Missing"));
        assertFalse(trip.editDestination("Missing", "Rome", "Description"));
        assertNull(trip.searchDestination("Missing"));
        assertNull(trip.searchDestination(null));
    }

    // A failed edit should leave the existing destination unchanged.
    @Test
    public void editDestinationRejectsInvalidNewName()
    {
        TripManager trip = new TripManager();
        trip.addDestination("Paris", "Description");
        assertFalse(trip.editDestination("Paris", "", "New"));
        assertEquals("Paris", trip.searchDestination("Paris").getName());
    }

    // Check that activities have the same basic management operations.
    @Test
    public void addsSearchesEditsAndRemovesActivity()
    {
        TripManager trip = new TripManager();
        assertTrue(trip.addActivity("Hiking", "Mountain trail"));
        assertEquals("Mountain trail",
            trip.searchActivity("Hiking").getDescription());
        assertTrue(trip.editActivity("Hiking", "Kayaking", "River"));
        assertNull(trip.searchActivity("Hiking"));
        assertEquals("River", trip.searchActivity("Kayaking").getDescription());
        assertTrue(trip.removeActivity("Kayaking"));
        assertNull(trip.searchActivity("Kayaking"));
    }

    // Invalid activity names and missing activities should be handled safely.
    @Test
    public void activityMethodsHandleInvalidInput()
    {
        TripManager trip = new TripManager();
        assertFalse(trip.addActivity("", "Description"));
        assertFalse(trip.removeActivity("Missing"));
        assertFalse(trip.editActivity("Missing", "Kayaking", "Description"));
        assertNull(trip.searchActivity("Missing"));
        assertNull(trip.searchActivity(null));
    }

    // A failed edit should leave the existing activity unchanged.
    @Test
    public void editActivityRejectsInvalidNewName()
    {
        TripManager trip = new TripManager();
        trip.addActivity("Hiking", "Description");
        assertFalse(trip.editActivity("Hiking", "  ", "New"));
        assertEquals("Hiking", trip.searchActivity("Hiking").getName());
    }

    // Display should work for both an empty trip and one with items.
    @Test
    public void displayTripHandlesEmptyAndPopulatedTrip()
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
            trip.addDestination("Paris", "Trip");
            trip.addActivity("Hiking", "Trip");
            trip.displayTrip();
            assertTrue(output.toString().contains("Destination: Paris"));
            assertTrue(output.toString().contains("Hiking"));
        }
        finally
        {
            System.setOut(oldOut);
        }
    }
}
