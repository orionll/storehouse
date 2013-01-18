package org.dive.storehouse.core;

import static com.google.common.base.Preconditions.*;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
public final class PersonId extends EntityId
{
    private PersonId(long id)
    {
        super(id);
    }

    public static PersonId valueOf(long id)
    {
        return new PersonId(id);
    }

    public static PersonId valueOf(String id)
    {
        checkArgument(id != null, "[id] must not be null");
        return new PersonId(Long.valueOf(id));
    }
}
