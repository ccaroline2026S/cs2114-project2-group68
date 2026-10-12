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
        if (destination == null)
        {
            return false;
        }
        return destinations.add(destination);
    }

    /** Removes the matching destination from the trip. */
    public boolean removeDestination(Destination destination)
    {
        Destination current = findDestination(destination);
        return current != null && destinations.contains(current)
            && destinations.remove(current);
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
        if (activity == null)
        {
            return false;
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
