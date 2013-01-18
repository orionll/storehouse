package org.dive.storehouse.core;

import static com.google.common.base.Preconditions.*;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.primitives.Longs;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
public abstract class EntityId
{
    private long id;

    EntityId(long id)
    {
        checkArgument(id >= 0, "[id] must be nonnegative");
        this.id = id;
    }

    @VisibleForTesting
    void setId(long id)
    {
        checkArgument(id >= 0, "[id] must be nonnegative");
        this.id = id;
    }

    public long getId()
    {
        return this.id;
    }

    @Override
    public final int hashCode()
    {
        return Longs.hashCode(this.id);
    }

    @Override
    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }

        EntityId other = (EntityId) obj;
        return this.id == other.id;
    }

    @Override
    public final String toString()
    {
        return Objects.toStringHelper(this).add("id", this.id).toString();
    }
}
