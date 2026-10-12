package adventurerGo;

import student.TestCase;

/**
 * Tests the Destination class.
 */
public class DestinationTest
    extends TestCase
{
    private Destination destination;

    /**
     * Sets up a destination before each test.
     */
    public void setUp()
    {
        destination = new Destination("Paris", "Visit the Eiffel Tower");
    }


    /**
     * Tests creating a destination.
     */
    public void testConstructor()
    {
        assertEquals("Paris", destination.getName());
        assertEquals("Visit the Eiffel Tower", destination.getDescription());
    }


    /**
     * Tests creating a destination with a bad name.
     */
    public void testBadConstructor()
    {
        try
        {
            new Destination("", "Visit the Eiffel Tower");
            fail("Should not allow an empty name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        try
        {
            new Destination(null, "Visit the Eiffel Tower");
            fail("Should not allow a null name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        try
        {
            new Destination("   ", "Visit the Eiffel Tower");
            fail("Should not allow a blank name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }
    }


    /**
     * Tests getting the destination name.
     */
    public void testGetName()
    {
        assertEquals("Paris", destination.getName());
    }


    /**
     * Tests that the name stays the same after bad input.
     */
    public void testGetNameBadInput()
    {
        try
        {
            destination.setName("");
            fail("Should not allow an empty name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        assertEquals("Paris", destination.getName());
    }


    /**
     * Tests getting the destination description.
     */
    public void testGetDescription()
    {
        assertEquals("Visit the Eiffel Tower", destination.getDescription());
    }


    /**
     * Tests getting an empty description.
     */
    public void testGetDescriptionBlank()
    {
        Destination blank = new Destination("Paris", "");

        assertEquals("", blank.getDescription());
    }


    /**
     * Tests changing the destination name.
     */
    public void testSetName()
    {
        destination.setName("Rome");

        assertEquals("Rome", destination.getName());
    }


    /**
     * Tests rejecting a bad destination name.
     */
    public void testBadSetName()
    {
        try
        {
            destination.setName("");
            fail("Should not allow an empty name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        try
        {
            destination.setName(null);
            fail("Should not allow a null name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        try
        {
            destination.setName("   ");
            fail("Should not allow a blank name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected exception
        }

        assertEquals("Paris", destination.getName());
    }


    /**
     * Tests changing the description.
     */
    public void testSetDescription()
    {
        destination.setDescription("Visit the Louvre");

        assertEquals("Visit the Louvre", destination.getDescription());
    }


    /**
     * Tests allowing a blank description.
     */
    public void testBlankDescription()
    {
        destination.setDescription("");
        assertEquals("", destination.getDescription());

        destination.setDescription(null);
        assertNull(destination.getDescription());
    }


    /**
     * Tests comparing matching destinations.
     */
    public void testEquals()
    {
        Destination same = new Destination("Paris", "Visit the Eiffel Tower");

        assertTrue(destination.equals(same));
        assertTrue(destination.equals(destination));
    }


    /**
     * Tests comparing destinations that do not match.
     */
    public void testBadEquals()
    {
        Destination different = new Destination("Paris", "Visit the Louvre");

        assertFalse(destination.equals(different));
        assertFalse(destination.equals(null));
        assertFalse(destination.equals("Paris"));
    }


    /**
     * Tests comparing destinations with null descriptions.
     */
    public void testEqualsNullDescription()
    {
        Destination first = new Destination("Paris", null);
        Destination second = new Destination("Paris", null);

        assertTrue(first.equals(second));
        assertFalse(first.equals(destination));
    }


    /**
     * Tests displaying the destination.
     */
    public void testToString()
    {
        assertEquals("Paris: Visit the Eiffel Tower", destination.toString());
    }


    /**
     * Tests displaying a destination without a description.
     */
    public void testBlankToString()
    {
        destination.setDescription("");

        assertEquals("Paris", destination.toString());

        destination.setDescription(null);

        assertEquals("Paris", destination.toString());
    }
}
