package org.dive.storehouse.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
public class PersonRepository extends Repository<Person, PersonId>
{
    @PersistenceContext
    private EntityManager entityManager;

    public PersonRepository()
    {
        super(Person.class);
    }
}
