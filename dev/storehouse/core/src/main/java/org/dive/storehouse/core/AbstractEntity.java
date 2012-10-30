package org.dive.storehouse.core;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/
public abstract class AbstractEntity
{
    public abstract EntityId getId();

    @Override
    public final int hashCode()
    {
        return Objects.hash(getId());
    }

    /**
     * Two entities are equal if and only if they have the same types and equal nonnull ids
     */
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

        EntityId id = getId();
        EntityId otherId = ((AbstractEntity) obj).getId();

        if (id == null || otherId == null)
        {
            return false;
        }

        return id.equals(otherId);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
