package adventurerGo;
/** Keeps track of the destinations and activities in a trip. */
public class TripManager
{
    private LinkedChain<Destination> destinations;
    private LinkedChain<Activity> activities;

    /** Starts a trip with no destinations or activities. */
    public TripManager()
    {
        destinations = new LinkedChain<Destination>();
        activities = new LinkedChain<Activity>();
    }

    /** Adds a destination to the trip. */
    public boolean addDestination(Destination destination)
    {
        if (destination == null || findDestination(destination) != null)
        {
            return false;
        }
        return destinations.add(destination);
    }

    /** Removes the matching destination from the trip. */
    public boolean removeDestination(Destination destination)
    {
        Destination current = findDestination(destination);
        if (current == null || !destinations.contains(current)
            || !destinations.remove(current))
        {
            return false;
        }
        for (Activity activity : activities.toArray(
            new Activity[activities.size()]))
        {
            if (activity.getDestination() == current)
            {
                activity.setDestination(null);
            }
        }
        return true;
    }

    /** Replaces an existing destination with the updated one. */
    public boolean editDestination(
        Destination oldDestination, Destination newDestination)
    {
        Destination current = findDestination(oldDestination);
        if (current == null || newDestination == null)
        {
            return false;
        }
        Destination duplicate = findDestination(newDestination);
        if (duplicate != null && duplicate != current)
        {
            return false;
        }
        try
        {
            current.setName(newDestination.getName());
            current.setDescription(newDestination.getDescription());
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Checks whether a destination with this name is in the trip. */
    public boolean searchDestination(Destination destination)
    {
        return findDestination(destination) != null;
    }

    // Match by name so callers can search with a partially filled object.
    private Destination findDestination(Destination destination)
    {
        if (destination == null || destination.getName() == null)
        {
            return null;
        }
        Destination[] items = destinations.toArray(
            new Destination[destinations.size()]);
        for (Destination item : items)
        {
            if (item.getName().equals(destination.getName()))
            {
                return item;
            }
        }
        return null;
    }

    /** Adds an activity to the trip. */
    public boolean addActivity(Activity activity)
    {
        if (activity == null || findActivity(activity) != null)
        {
            return false;
        }
        if (activity.getDestination() != null)
        {
            Destination destination = findDestination(activity.getDestination());
            if (destination == null)
            {
                return false;
            }
            activity.setDestination(destination);
        }
        return activities.add(activity);
    }

    /** Removes the matching activity from the trip. */
    public boolean removeActivity(Activity activity)
    {
        Activity match = findActivity(activity);
        return match != null && activities.remove(match);
    }

    /** Replaces an existing activity with the updated one. */
    public boolean editActivity(Activity oldActivity, Activity newActivity)
    {
        Activity current = findActivity(oldActivity);
        if (current == null || newActivity == null)
        {
            return false;
        }
        Activity duplicate = findActivity(newActivity);
        if (duplicate != null && duplicate != current)
        {
            return false;
        }
        try
        {
            current.setName(newActivity.getName());
            current.setDescription(newActivity.getDescription());
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Checks whether an activity with this name is in the trip. */
    public boolean searchActivity(Activity activity)
    {
        return findActivity(activity) != null;
    }

    // Activities are also matched by name for searches and edits.
    private Activity findActivity(Activity activity)
    {
        if (activity == null || activity.getName() == null)
        {
            return null;
        }
        Activity[] items = activities.toArray(
            new Activity[activities.size()]);
        for (Activity item : items)
        {
            if (item.getName().equals(activity.getName()))
            {
                return item;
            }
        }
        return null;
    }

    /** Links an existing activity to an existing destination; null unlinks it. */
    public boolean associateActivity(Activity activity, Destination destination)
    {
        Activity current = findActivity(activity);
        Destination target = findDestination(destination);
        if (current == null || (destination != null && target == null))
        {
            return false;
        }
        current.setDestination(target);
        return true;
    }

    /** Updates a destination's separate travel/lodging estimate. */
    public boolean setDestinationCost(Destination destination, double cost)
    {
        Destination current = findDestination(destination);
        if (current == null)
        {
            return false;
        }
        current.setEstimatedCost(cost);
        return true;
    }

    /** Updates an activity's estimate. */
    public boolean setActivityCost(Activity activity, double cost)
    {
        Activity current = findActivity(activity);
        if (current == null)
        {
            return false;
        }
        current.setEstimatedCost(cost);
        return true;
    }

    /** Sums each destination and activity once, including unassigned activities. */
    public double estimateTripCost()
    {
        java.math.BigDecimal total = java.math.BigDecimal.ZERO;
        for (Destination destination : destinations.toArray(
            new Destination[destinations.size()]))
        {
            total = total.add(java.math.BigDecimal.valueOf(
                destination.getEstimatedCost()));
        }
        for (Activity activity : activities.toArray(
            new Activity[activities.size()]))
        {
            total = total.add(java.math.BigDecimal.valueOf(
                activity.getEstimatedCost()));
        }
        double result = total.doubleValue();
        if (!Double.isFinite(result))
        {
            throw new IllegalArgumentException("Trip cost is too large.");
        }
        return result;
    }

    /** Prints all destinations and activities in the trip. */
    public void displayTrip()
    {
        System.out.println("===== AdventurerGo Trip =====");
        System.out.println();
        System.out.println("Destinations - ");
        if (destinations.isEmpty())
        {
            System.out.println("No destinations added yet.");
        }
        else
        {
            for (Destination destination : destinations.toArray(
                new Destination[destinations.size()]))
            {
                System.out.println(destination);
            }
        }

        System.out.println();
        System.out.println("Activities - ");
        if (activities.isEmpty())
        {
            System.out.println("No activities added yet.");
        }
        else
        {
            for (Activity activity : activities.toArray(
                new Activity[activities.size()]))
            {
                System.out.println(activity);
            }
        }
    }
}
