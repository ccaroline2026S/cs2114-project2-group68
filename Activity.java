/**
 * Represents an activity that someone plans to do on a trip.
 * Each activity has a name and a description.
 *
 * @author Noah Bierman
 * @version 2026.10.11
 */

public class Activity
{
    private String name;
    private String description;

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
     * Returns the activity information as a String.
     *
     * @return the activity name and description
     */
    @Override
    public String toString() 
    {
        if (description == null || description.isEmpty()) 
        {
            return name;
        }

        return name + ": " + description;
    }


    /**
     * Returns a hash code based on the activity information.
     *
     * @return the activity hash code
     */
    @Override
    public int hashCode() 
    {
        int result = name.hashCode();

        if (description != null)
        {
            result = 31 * result + description.hashCode();
        }

        return result;
    }
}