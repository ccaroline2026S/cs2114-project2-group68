package adventurerGo;

import static org.junit.Assert.*;
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
        TripManager trip = new TripManager();
        trip.displayTrip();

        trip.addDestination(new Destination("Paris", "Trip"));
        trip.addActivity(new Activity("Hiking", "Trip"));
        trip.displayTrip();
    }
    @Test
    public void linksUseStoredDestinationsAndSurviveRenaming()
    {
        TripManager trip = new TripManager();
        Destination paris = new Destination("Paris", "City");
        Destination rome = new Destination("Rome", "City");
        Activity tour = new Activity("Tour", "Walking");
        trip.addDestination(paris);
        trip.addDestination(rome);
        trip.addActivity(tour);
        assertTrue(trip.associateActivity(new Activity("Tour", ""),
            new Destination("Paris", "")));
        assertSame(paris, tour.getDestination());
        assertTrue(trip.editDestination(paris, new Destination("Lyon", "City")));
        assertEquals("Lyon", tour.getDestination().getName());
        assertTrue(trip.editActivity(tour, new Activity("Walk", "Updated")));
        assertSame(paris, tour.getDestination());
        assertTrue(trip.associateActivity(tour, rome));
        assertSame(rome, tour.getDestination());
        assertTrue(trip.removeDestination(rome));
        assertNull(tour.getDestination());
        assertTrue(trip.searchActivity(tour));
        assertTrue(trip.associateActivity(tour, paris));
        assertTrue(trip.associateActivity(tour, null));
        assertNull(tour.getDestination());
    }

    @Test
    public void invalidLinksAndDuplicateNamesDoNotChangeTrip()
    {
        TripManager trip = new TripManager();
        Destination paris = new Destination("Paris", "");
        Activity tour = new Activity("Tour", "", new Destination("Paris", ""));
        assertFalse(trip.addActivity(tour));
        trip.addDestination(paris);
        assertTrue(trip.addActivity(tour));
        assertSame(paris, tour.getDestination());
        assertFalse(trip.addDestination(new Destination("Paris", "Other")));
        assertFalse(trip.addActivity(new Activity("Tour", "Other")));
        assertFalse(trip.associateActivity(tour, new Destination("Missing", "")));
        assertSame(paris, tour.getDestination());
        assertFalse(trip.associateActivity(null, paris));
        assertFalse(trip.associateActivity(new Activity("Missing", ""), paris));
        Destination rome = new Destination("Rome", "");
        Activity swim = new Activity("Swim", "");
        trip.addDestination(rome);
        trip.addActivity(swim);
        assertFalse(trip.editDestination(rome, new Destination("Paris", "")));
        assertFalse(trip.editActivity(swim, new Activity("Tour", "")));
        assertEquals("Rome", rome.getName());
        assertEquals("Swim", swim.getName());
    }

    @Test
    public void estimatesCountEachItemOnceAndTrackChanges()
    {
        TripManager trip = new TripManager();
        assertEquals(0.0, trip.estimateTripCost(), 0.0);
        Destination paris = new Destination("Paris", "");
        Activity tour = new Activity("Tour", "");
        Activity swim = new Activity("Swim", "");
        trip.addDestination(paris);
        trip.addActivity(tour);
        trip.addActivity(swim);
        assertTrue(trip.setDestinationCost(new Destination("Paris", ""), 100));
        assertTrue(trip.setActivityCost(new Activity("Tour", ""), 12.50));
        trip.setActivityCost(swim, 0.25);
        assertEquals(112.75, trip.estimateTripCost(), 0.0);
        trip.associateActivity(tour, paris);
        trip.associateActivity(swim, paris);
        assertEquals(112.75, trip.estimateTripCost(), 0.0);
        trip.editDestination(paris, new Destination("Rome", ""));
        trip.editActivity(tour, new Activity("Walk", ""));
        assertEquals(112.75, trip.estimateTripCost(), 0.0);
        trip.setActivityCost(tour, 20);
        assertEquals(120.25, trip.estimateTripCost(), 0.0);
        trip.removeDestination(paris);
        assertEquals(20.25, trip.estimateTripCost(), 0.0);
        trip.removeActivity(tour);
        assertEquals(0.25, trip.estimateTripCost(), 0.0);
        trip.removeActivity(swim);
        assertEquals(0.0, trip.estimateTripCost(), 0.0);
    }

    @Test
    public void estimateHandlesDecimalsMissingItemsAndOverflow()
    {
        TripManager trip = new TripManager();
        Destination city = new Destination("City", "");
        Activity tour = new Activity("Tour", "");
        assertFalse(trip.setDestinationCost(city, 10));
        assertFalse(trip.setActivityCost(tour, 10));
        assertFalse(trip.setDestinationCost(null, 10));
        assertFalse(trip.setActivityCost(null, 10));
        trip.addDestination(city);
        trip.addActivity(tour);
        trip.setDestinationCost(city, 0.1);
        trip.setActivityCost(tour, 0.2);
        assertEquals(0.3, trip.estimateTripCost(), 0.0);
        double[] invalid = {-1, Double.NaN, Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY};
        for (double cost : invalid)
        {
            try
            {
                trip.setDestinationCost(city, cost);
                fail("Accepted invalid destination cost");
            }
            catch (IllegalArgumentException e)
            {
                assertEquals(0.3, trip.estimateTripCost(), 0.0);
            }
            try
            {
                trip.setActivityCost(tour, cost);
                fail("Accepted invalid activity cost");
            }
            catch (IllegalArgumentException e)
            {
                assertEquals(0.3, trip.estimateTripCost(), 0.0);
            }
        }
        trip.setDestinationCost(city, Double.MAX_VALUE);
        trip.setActivityCost(tour, 0);
        assertEquals(Double.MAX_VALUE, trip.estimateTripCost(), 0.0);
        trip.setActivityCost(tour, Double.MAX_VALUE);
        try
        {
            trip.estimateTripCost();
            fail("Should reject overflowing total");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("Trip cost is too large.", e.getMessage());
        }
    }
}
