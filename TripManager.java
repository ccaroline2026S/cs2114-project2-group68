/** Keeps track of the destinations and activities in a trip. */
public class TripManager
{
    // LinkedChain is the collection type provided for this project.
    private LinkedChain<Destination> destinations;
    private LinkedChain<Activity> activities;

    /** Starts a trip with no destinations or activities. */
    public TripManager()
    {
        destinations = new LinkedChain<Destination>();
        activities = new LinkedChain<Activity>();
    }

    /** Adds a destination, returning false if its name is invalid. */
    public boolean addDestination(String name, String description)
    {
        try
        {
            destinations.add(new Destination(name, description));
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Removes a destination by name. */
    public boolean removeDestination(String name)
    {
        Destination destination = searchDestination(name);
        return destination != null && destinations.remove(destination);
    }

    /** Updates the destination with the given old name. */
    public boolean editDestination(
        String oldName, String newName, String newDescription)
    {
        Destination destination = searchDestination(oldName);
        if (destination == null)
        {
            return false;
        }
        try
        {
            destination.setName(newName);
            destination.setDescription(newDescription);
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Finds a destination by name, or returns null if it is not present. */
    public Destination searchDestination(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return null;
        }
        // toArray lets the manager search using the LinkedChain API.
        Destination[] items = destinations.toArray(
            new Destination[destinations.size()]);
        for (Destination destination : items)
        {
            if (destination.getName().equals(name))
            {
                return destination;
            }
        }
        return null;
    }

    /** Adds an activity, returning false if its name is invalid. */
    public boolean addActivity(String name, String description)
    {
        try
        {
            activities.add(new Activity(name, description));
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Removes an activity by name. */
    public boolean removeActivity(String name)
    {
        Activity activity = searchActivity(name);
        return activity != null && activities.remove(activity);
    }

    /** Updates the activity with the given old name. */
    public boolean editActivity(
        String oldName, String newName, String newDescription)
    {
        Activity activity = searchActivity(oldName);
        if (activity == null)
        {
            return false;
        }
        try
        {
            activity.setName(newName);
            activity.setDescription(newDescription);
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    /** Finds an activity by name, or returns null if it is not present. */
    public Activity searchActivity(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return null;
        }
        // Use the same search approach as destinations.
        Activity[] items = activities.toArray(
            new Activity[activities.size()]);
        for (Activity activity : items)
        {
            if (activity.getName().equals(name))
            {
                return activity;
            }
        }
        return null;
    }

    /** Prints all destinations and activities in the trip. */
    public void displayTrip()
    {
        System.out.println("===== AdventurerGo Trip =====");
        System.out.println("\nDestinations:");
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

        System.out.println("\nActivities:");
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
