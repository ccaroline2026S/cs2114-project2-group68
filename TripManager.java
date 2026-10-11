public class TripManager
{
    private LinkedChain<Destination> destinations;
    private LinkedChain<Activity> activities;

    public TripManager()
    {
        destinations = new LinkedChain<Destination>();
        activities = new LinkedChain<Activity>();
    }

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

    public boolean removeDestination(String name)
    {
        Destination destination = searchDestination(name);
        return destination != null && destinations.remove(destination);
    }

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

    public Destination searchDestination(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return null;
        }
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

    public boolean removeActivity(String name)
    {
        Activity activity = searchActivity(name);
        return activity != null && activities.remove(activity);
    }

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

    public Activity searchActivity(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return null;
        }
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
