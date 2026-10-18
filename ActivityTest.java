package adventurerGo;

import student.TestCase;

/**
 * Tests the Activity class.
 */

public class ActivityTest
    extends TestCase
{
    private Activity activity;

    /**
     * Sets up an activity for testing.
     */
    public void setUp()
    {
        activity = new Activity("Hike", "Morning sunrise hike");
    }


    /**
     * Tests creating an activity.
     */
    public void testConstructor()
    {
        assertEquals("Hike", activity.getName());
        assertEquals("Morning sunrise hike", activity.getDescription());
    }


    /**
     * Tests creating an activity with a bad name.
     */
    public void testBadConstructor()
    {
        try
        {
            new Activity("", "Morning hike");
            fail("Should not allow an empty name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        try
        {
            new Activity(null, "Morning hike");
            fail("Should not allow a null name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        try
        {
            new Activity("   ", "Morning hike");
            fail("Should not allow a blank name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }
    }


    /**
     * Tests getting the name.
     */
    public void testGetName()
    {
        assertEquals("Hike", activity.getName());
    }


    /**
     * Tests getting the description.
     */
    public void testGetDescription()
    {
        assertEquals("Morning sunrise hike", activity.getDescription());
    }


    /**
     * Tests changing the name.
     */
    public void testSetName()
    {
        activity.setName("Museum Tour");
        assertEquals("Museum Tour", activity.getName());
    }


    /**
     * Tests changing the name to something invalid.
     */
    public void testBadSetName()
    {
        try
        {
            activity.setName("");
            fail("Should not allow an empty name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        try
        {
            activity.setName(null);
            fail("Should not allow a null name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        try
        {
            activity.setName("   ");
            fail("Should not allow a blank name");
        }
        catch (IllegalArgumentException e)
        {
            // Expected
        }

        assertEquals("Hike", activity.getName());
    }


    /**
     * Tests changing the description.
     */
    public void testSetDescription()
    {
        activity.setDescription("Evening hike");
        assertEquals("Evening hike", activity.getDescription());
    }


    /**
     * Tests that the description can be blank.
     */
    public void testBlankDescription()
    {
        activity.setDescription("");
        assertEquals("", activity.getDescription());
    }


    /**
     * Tests getting a blank description.
     */
    public void testGetBlankDescription()
    {
        Activity blank = new Activity("Hike", "");
        assertEquals("", blank.getDescription());
    }


    /**
     * Tests comparing two activities.
     */
    public void testEquals()
    {
        Activity same = new Activity("Hike", "Morning sunrise hike");
        Activity different = new Activity("Swimming", "At the beach");

        assertTrue(activity.equals(same));
        assertFalse(activity.equals(different));

        Activity differentDescription = new Activity("Hike", "Evening hike");

        assertFalse(activity.equals(differentDescription));
    }


    /**
     * Tests comparing with null and a different type.
     */
    public void testBadEquals()
    {
        assertFalse(activity.equals(null));
        assertFalse(activity.equals("Hike"));
    }


    /**
     * Tests displaying the activity.
     */
    public void testToString()
    {
        assertEquals("Hike: Morning sunrise hike", activity.toString());
    }


    /**
     * Tests displaying an activity with no description.
     */
    public void testBlankToString()
    {
        activity.setDescription("");
        assertEquals("Hike", activity.toString());
    }
    
    /**
     * Tests creating an activity with a destination.
     */
    public void testConstructorWithDestination()
    {
        Destination paris = new Destination("Paris", "France");
        Activity tour = new Activity(
            "Eiffel Tower Tour", "Afternoon visit", paris);

        assertEquals("Eiffel Tower Tour", tour.getName());
        assertEquals("Afternoon visit", tour.getDescription());
        assertSame(paris, tour.getDestination());
    }


    /**
     * Tests rejecting invalid names when creating a linked activity.
     */
    public void testBadLinkedConstructor()
    {
        Destination paris = new Destination("Paris", "France");
        String[] invalidNames = { "", null, "   " };

        for (String invalidName : invalidNames)
        {
            try
            {
                new Activity(invalidName, "Afternoon visit", paris);
                fail("Should reject an invalid name");
            }
            catch (IllegalArgumentException e)
            {
                // Expected
            }
        }
    }


    /**
     * Tests activities without an assigned destination.
     */
    public void testUnassignedDestination()
    {
        assertNull(activity.getDestination());

        Activity unassigned = new Activity("Hike", "Morning hike", null);

        assertNull(unassigned.getDestination());
    }


    /**
     * Tests assigning and changing a destination.
     */
    public void testSetDestination()
    {
        Destination paris = new Destination("Paris", "France");
        Destination rome = new Destination("Rome", "Italy");

        activity.setDestination(paris);
        assertSame(paris, activity.getDestination());

        activity.setDestination(rome);
        assertSame(rome, activity.getDestination());
    }


    /**
     * Tests removing a destination link.
     */
    public void testClearDestination()
    {
        activity.setDestination(new Destination("Paris", "France"));
        activity.setDestination(null);

        assertNull(activity.getDestination());
        assertEquals("Hike: Morning sunrise hike", activity.toString());
    }


    /**
     * Tests displaying an activity with a destination.
     */
    public void testToStringWithDestination()
    {
        activity.setDestination(new Destination("Paris", "France"));

        assertEquals(
            "Hike: Morning sunrise hike [Destination: Paris]",
            activity.toString());

        activity.setDescription("");
        assertEquals("Hike [Destination: Paris]", activity.toString());

        activity.setDescription(null);
        assertEquals("Hike [Destination: Paris]", activity.toString());
    }


    /**
     * Tests that renaming the destination updates the activity display.
     */
    public void testLinkedDestinationRename()
    {
        Destination destination = new Destination("Paris", "France");
        activity.setDestination(destination);

        destination.setName("Rome");

        assertSame(destination, activity.getDestination());
        assertEquals(
            "Hike: Morning sunrise hike [Destination: Rome]",
            activity.toString());
    }


    /**
     * Tests that destination links do not change the matching rules.
     */
    public void testEqualsWithDestination()
    {
        Activity same = new Activity("Hike", "Morning sunrise hike");

        activity.setDestination(new Destination("Paris", "France"));
        same.setDestination(new Destination("Rome", "Italy"));

        assertTrue(activity.equals(same));
        assertTrue(same.equals(activity));
    }
}
