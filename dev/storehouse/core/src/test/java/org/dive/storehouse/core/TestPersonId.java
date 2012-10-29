package org.dive.storehouse.core;

import static org.fest.assertions.Assertions.*;

import org.testng.annotations.Test;

/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
public class TestPersonId
{
    @Test
    public void testValueOfLong()
    {
        assertThat(PersonId.valueOf(200).getId()).isEqualTo(200);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testValueOfNullString()
    {
        PersonId.valueOf((String) null);
    }

    @Test
    public void testValueOfNotNullString()
    {
        assertThat(PersonId.valueOf("200").getId()).isEqualTo(200);
    }
}