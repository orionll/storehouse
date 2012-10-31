package org.dive.storehouse.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
@Entity
@Table
public class Item extends AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 2, max = 512)
    private String name;

    private String description;

    @ManyToOne
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
