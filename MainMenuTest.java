package adventurerGo;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class MainMenuTest
{
    private MainMenu menu;
    
    @Before
    public void setUp()
    {
        menu = new MainMenu();
    }
    
    @Test
    public void testConstructor() {
        assertNotNull(menu);
    }
    
    @Test
    public void testMultipleMenus() {
        assertNotNull(menu);
        MainMenu secondMenu = new MainMenu();
        assertNotSame(menu, secondMenu);
    }
    private String runMenu(String input)
    {
        java.io.InputStream oldIn = System.in;
        java.io.PrintStream oldOut = System.out;
        java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
        try
        {
            System.setIn(new java.io.ByteArrayInputStream(
                input.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
            System.setOut(new java.io.PrintStream(output));
            new MainMenu().run();
            return output.toString();
        }
        finally
        {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }

    @Test
    public void numericNamesRepromptForCreationAndRenaming()
    {
        String output = runMenu("1\n123\nParis\nCity\n"
            + "5\n456\nTour\nWalk\n"
            + "3\nParis\n789\nRome\nCity\n"
            + "7\nTour\n12.5\n5K Run\nRace\n0\n9\n");
        assertEquals(4, output.split("Name cannot be blank or only a number", -1).length - 1);
        assertTrue(output.contains("Rome: City"));
        assertTrue(output.contains("5K Run: Race"));
        assertFalse(output.contains("123 has been successfully added"));
        assertFalse(output.contains("edited to 789"));
    }

    @Test
    public void menuEstimatesCostsAndRepromptsInvalidInput()
    {
        String output = runMenu("1\nParis\nCity\n5\nTour\nWalk\n"
            + "10\nTour\nParis\n"
            + "11\nParis\nhello\n-1\nNaN\nInfinity\n1e309\n100.25\n"
            + "12\nTour\n-0.01\n12.50\n13\n0\n9\n");
        assertEquals(6, output.split("Invalid cost.", -1).length - 1);
        assertTrue(output.contains("Estimated trip cost: 112.75"));
        assertTrue(output.contains("[Destination: Paris]"));
    }

    @Test
    public void menuHandlesEmptyTripMissingItemsAndEndOfInput()
    {
        String output = runMenu("13\n10\nMissing\nCity\n"
            + "11\nMissing\n0\n12\nMissing\n0\n9\n");
        assertTrue(output.contains("Estimated trip cost: 0.00"));
        assertTrue(output.contains("Activity or destination was not found."));
        assertEquals(2, output.split("Item was not found.", -1).length - 1);
        assertTrue(runMenu("1\n123\n").contains("Try again:"));
    }

    @Test
    public void menuUnlinksAndRejectsDuplicateNames()
    {
        String output = runMenu("1\nParis\nCity\n1\nParis\nOther\n"
            + "5\nTour\nWalk\n5\nTour\nOther\n"
            + "10\nTour\nParis\n10\nTour\n\n0\n9\n");
        assertTrue(output.contains("Destination name already exists."));
        assertTrue(output.contains("Activity name already exists."));
        assertFalse(output.contains("[Destination: Paris]"));
    }
}
