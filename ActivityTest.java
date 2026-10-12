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
}
