/**
 * Represents a destination that someone wants to visit
 * during their trip.
 *
 * @author Noah Bierman
 * @version 2026.10.11
 */
public class Destination 
{

    private String name;
    private String description;

    /**
     * Creates a destination with a name and description.
     *
     * @param name the destination name
     * @param description details about the destination
     */
    public Destination(String name, String description) 
    {
        setName(name);
        setDescription(description);
    }


    /**
     * Returns the destination's name.
     *
     * @return the destination name
     */
    public String getName() 
    {
        return name;
    }


    /**
     * Returns the destination's description.
     *
     * @return the destination description
     */
    public String getDescription() 
    {
        return description;
    }


    /**
     * Changes the destination's name.
     *
     * @param name the new destination name
     */
    public void setName(String name) 
    {
        if (name == null || name.trim().isEmpty()) 
        {
            throw new IllegalArgumentException(
                "Destination name cannot be blank.");
        }

        this.name = name;
    }


    /**
     * Changes the destination's description.
     *
     * @param description the new description
     */
    public void setDescription(String description) 
    {
        this.description = description;
    }


    /**
     * Checks if two destinations have the same information.
     *
     * @param other the object being compared
     * @return true if the destinations match
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
        
        Destination otherDestination = (Destination)other;

        if (!name.equals(otherDestination.name))
        {
            return false;
        }

        if (description == null)
        {
            return otherDestination.description == null;
        }

        return description.equals(otherDestination.description);
    }

    /**
     * Returns the destination information as a String.
     *
     * @return the destination name and description
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
     * Returns a hash code for the destination.
     *
     * @return the destination hash code
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

