package adventurerGo;

/**
 * Represents an activity that someone plans to do on a trip. Each activity has
 * a name and a description.
 */

public class Activity
{
    private String name;
    private String description;
    private Destination destination;

    /**
     * Creates a new activity with a name and description.
     *
     * @param name the name of the activity
     * @param description details about the activity
     */
    public Activity(String name, String description)
    {
        setName(name);
        setDescription(description);
    }
    
    /**
     * Creates an activity linked to a destination.
     *
     * @param name the activity name
     * @param description details about the activity
     * @param destination the destination for this activity
     */
    public Activity(
        String name,
        String description,
        Destination destination)
    {
        this(name, description);
        setDestination(destination);
    }


    /**
     * Returns the name of the activity.
     *
     * @return the activity name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns the description of the activity.
     *
     * @return the activity description
     */
    public String getDescription()
    {
        return description;
    }


    /**
     * Changes the name of the activity.
     *
     * @param name the new activity name
     */
    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                "Activity name cannot be blank.");
        }

        this.name = name;
    }


    /**
     * Changes the description of the activity.
     *
     * @param description the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }


    /**
     * Checks if two activities have the same information.
     *
     * @param other the object to compare
     * @return true if they are the same
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }

        if (other == null)
        {
            return false;
        }

        if (!this.getClass().equals(other.getClass()))
        {
            return false;
        }

        Activity otherActivity = (Activity)other;

        if (!name.equals(otherActivity.name))
        {
            return false;
        }

        if (description == null)
        {
            return otherActivity.description == null;
        }

        return description.equals(otherActivity.description);
    }


    /**
     * Returns the activity information and its linked destination.
     *
     * @return the formatted activity information
     */
    @Override
    public String toString()
    {
        String result = name;

        if (description != null && !description.isEmpty())
        {
            result += ": " + description;
        }

        if (destination != null)
        {
            result += " [Destination: " + destination.getName() + "]";
        }

        return result;
    }

    
    /**
     * Returns the destination linked to this activity.
     *
     * @return the destination, or null if unassigned
     */
    public Destination getDestination()
    {
        return destination;
    }


    /**
     * Changes the destination linked to this activity.
     *
     * @param destination the destination, or null to remove the link
     */
    public void setDestination(Destination destination)
    {
        this.destination = destination;
    }
}
