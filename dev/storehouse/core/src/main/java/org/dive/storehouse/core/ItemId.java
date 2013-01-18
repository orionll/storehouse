package org.dive.storehouse.core;

import static com.google.common.base.Preconditions.*;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
public final class ItemId extends EntityId
{
    private ItemId(long id)
    {
        super(id);
    }

    public static ItemId valueOf(long id)
    {
        return new ItemId(id);
    }

    public static ItemId valueOf(String id)
    {
        checkArgument(id != null, "[id] must not be null");
        return new ItemId(Long.valueOf(id));
    }
}
