package org.dive.storehouse.core;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created 30.10.2012
 * @author orionll
 *
**/
@Startup
@Singleton
public class InitialFill
{
    private static final Logger LOG = LoggerFactory.getLogger(InitialFill.class);

    @Inject
    private ItemRepository itemRepository;
    @Inject
    private PersonRepository personRepository;

    @PostConstruct
    public void fillInitialData()
    {
        Person johnDoe = createPerson("John", "Doe", "Born in 1989", "+40231899999");
        Person jamesPrescott = createPerson("James", "Prescott", "Lives in New York", null);
        createPerson("Linda", "Hamilton", "Born in 1954, doesn't smoke", "+40000000002");

        createItem("Ascender", null, null);
        createItem("Ascender", null, johnDoe);
        createItem("TV", "Sony", johnDoe);
        createItem("Notebook Toshiba", "Toshiba Satellite", jamesPrescott);
        createItem("Notebook Sony", "Sony Vaio EB4M1R/BQ", jamesPrescott);
        createItem("Lamp", null, jamesPrescott);
    }

    private Person createPerson(String firstName,
                                String lastName,
                                String description,
                                String phoneNumber)
    {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDescription(description);
        person.setPhoneNumber(phoneNumber);

        this.personRepository.persist(person);
        LOG.info("Created {}", person);
        return person;
    }

    private Item createItem(String name,
                            String description,
                            Person owner)
    {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setCurrentOwner(owner);

        this.itemRepository.persist(item);
        LOG.info("Created {}", item);
        return item;
    }
}
