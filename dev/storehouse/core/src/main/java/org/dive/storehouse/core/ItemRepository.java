package org.dive.storehouse.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
public class ItemRepository extends Repository<Item, ItemId>
{
    @PersistenceContext
    private EntityManager entityManager;

    public ItemRepository()
    {
        super(Item.class);
    }
}
