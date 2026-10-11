public class Destination
{
    private String name;
    private String description;

    public Destination(String name, String description)
    {
        setName(name);
        setDescription(description);
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

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

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(name, description);
    }

    @Override
    public String toString()
    {
        return "Destination: " + name + "\nDescription: " + description;
    }
}
