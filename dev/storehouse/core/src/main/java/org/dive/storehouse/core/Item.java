package org.dive.storehouse.core;


/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
public class Item extends AbstractEntity
{
    private Long id;
    private String name;
    private String description;
    private Person currentOwner;

    @Override
    public ItemId getId()
    {
        return (this.id == null) ? null : ItemId.valueOf(this.id);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Person getCurrentOwner()
    {
        return this.currentOwner;
    }

    public void setCurrentOwner(Person person)
    {
        this.currentOwner = person;
    }
}
