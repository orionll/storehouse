package org.dive.storehouse.core;

import javax.ejb.Stateless;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
@Stateless
public class PersonRepository extends Repository<Person, PersonId>
{
    public PersonRepository()
    {
        super(Person.class);
    }
}
