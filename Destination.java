package adventurerGo;


/**
 * Represents a destination that someone wants to visit during their trip.
 */
public class Destination
{

    private String name;
    private String description;
    private double estimatedCost;

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

        if (name.trim().matches(
            "[+-]?(?:\\p{Nd}+(?:\\.\\p{Nd}*)?|\\.\\p{Nd}+)"
                + "(?:[eE][+-]?\\p{Nd}+)?"))
        {
            throw new IllegalArgumentException(
                "Destination name cannot be only a number.");
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


    /** Returns this item's estimated cost, initially zero. */
    public double getEstimatedCost()
    {
        return estimatedCost;
    }


    /** Sets a finite, nonnegative cost in the trip's currency. */
    public void setEstimatedCost(double cost)
    {
        if (!Double.isFinite(cost) || cost < 0)
        {
            throw new IllegalArgumentException(
                "Cost must be a finite, nonnegative number.");
        }
        estimatedCost = cost;
    }
}
