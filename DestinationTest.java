import static org.junit.Assert.*;
import org.junit.Test;

/** Tests the basic behavior of Destination. */
public class DestinationTest
{
    // The constructor should save both values for the getters.
    @Test
    public void constructorAndGetters()
    {
        Destination destination = new Destination("Paris", "Eiffel Tower");
        assertEquals("Paris", destination.getName());
        assertEquals("Eiffel Tower", destination.getDescription());
    }

    // A blank name is not a valid destination name.
    @Test
    public void constructorRejectsInvalidName()
    {
        try
        {
            new Destination("  ", "Description");
            fail("Expected an exception");
        }
        catch (IllegalArgumentException e)
        {
            // expected
        }
    }

    @Test
    public void setNameUpdatesName()
    {
        Destination destination = new Destination("Paris", "Description");
        destination.setName("Rome");
        assertEquals("Rome", destination.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNameRejectsBlankName()
    {
        new Destination("Paris", "Description").setName("");
    }

    @Test
    public void setDescriptionUpdatesDescription()
    {
        Destination destination = new Destination("Paris", "Old");
        destination.setDescription("New");
        assertEquals("New", destination.getDescription());
    }

    @Test
    public void equalsAndHashCodeUseDestinationInformation()
    {
        Destination first = new Destination("Paris", "Trip");
        Destination same = new Destination("Paris", "Trip");
        Destination different = new Destination("Paris", "Other");
        assertEquals(first, same);
        assertEquals(first.hashCode(), same.hashCode());
        assertNotEquals(first, different);
        assertNotEquals(first, null);
        assertNotEquals(first, "Paris");
    }

    @Test
    public void toStringIncludesNameAndDescription()
    {
        Destination destination = new Destination("Paris", "Trip");
        assertEquals("Destination: Paris\nDescription: Trip",
            destination.toString());
    }
}
