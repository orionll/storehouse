package org.dive.storehouse.core;

import static com.google.common.base.Preconditions.*;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
public class Person extends AbstractEntity
{
    private Long id;
    private String firstName;
    private String lastName;
    private String description;

    @Override
    public PersonId getId()
    {
        return (this.id == null) ? null : PersonId.valueOf(this.id);
    }

    @VisibleForTesting
    void setId(PersonId personId)
    {
        checkArgument(personId != null, "[personId] must not be null");
        this.id = personId.getId();
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
