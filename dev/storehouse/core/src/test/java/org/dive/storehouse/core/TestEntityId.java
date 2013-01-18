package org.dive.storehouse.core;

import org.testng.annotations.Test;

/**
 * Created 18.01.2013
 * @author orionll
 *
**/
public class TestEntityId
{
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testConstructorWithNegativeId()
    {
        EntityId entityId = new EntityId(-173)
        {};
        entityId.getId();
    }

    @Test
    public void testConstructorWithZeroId()
    {
        EntityId entityId = new EntityId(0)
        {};
        entityId.getId();
        // Should not throw any exceptions
    }

    @Test
    public void testConstructorWithPositiveId()
    {
        EntityId entityId = new EntityId(199)
        {};
        entityId.getId();
        // Should not throw any exceptions
    }
}
