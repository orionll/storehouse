package org.dive.storehouse.core;

import static org.fest.assertions.Assertions.*;

import org.testng.annotations.Test;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
public class TestItemId
{
    @Test
    public void testValueOfLong()
    {
        assertThat(ItemId.valueOf(100).getId()).isEqualTo(100);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testValueOfNullString()
    {
        ItemId.valueOf((String) null);
    }

    @Test
    public void testValueOfNotNullString()
    {
        assertThat(ItemId.valueOf("100").getId()).isEqualTo(100);
    }
}
