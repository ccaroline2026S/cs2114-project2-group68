/** Stores the name and description for one trip destination. */
public class Destination
{
    // A destination must have a name, but its description can be changed later.
    private String name;
    private String description;

    /** Creates a destination and checks that its name is valid. */
    public Destination(String name, String description)
    {
        setName(name);
        setDescription(description);
    }

    /** Returns the destination name. */
    public String getName()
    {
        return name;
    }

    /** Returns the destination description. */
    public String getDescription()
    {
        return description;
    }

    /** Changes the name, rejecting null or blank values. */
    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    /** Changes the description. */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /** Destinations are equal when both their names and descriptions match. */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Destination))
        {
            return false;
        }
        Destination destination = (Destination)other;
        return name.equals(destination.name)
            && java.util.Objects.equals(description, destination.description);
    }

    /** Uses the same fields as equals so equal destinations share a hash code. */
    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(name, description);
    }

    /** Returns a readable version of the destination. */
    @Override
    public String toString()
    {
        return "Destination: " + name + "\nDescription: " + description;
    }
}
