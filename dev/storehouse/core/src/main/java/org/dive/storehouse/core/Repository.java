package org.dive.storehouse.core;

import static com.google.common.base.Preconditions.*;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.google.common.annotations.VisibleForTesting;

/**
 * Created 30.10.2012
 * @author orionll
 * 
**/
public abstract class Repository<E extends AbstractEntity, I extends EntityId>
{
    @PersistenceContext
    private EntityManager entityManager;

    private Class<E> entityClass;

    Repository(Class<E> entityClass)
    {
        checkArgument(entityClass != null, "[entityClass] must not be null");
        this.entityClass = entityClass;
    }

    @VisibleForTesting
    void setEntityClass(Class<E> entityClass)
    {
        checkArgument(entityClass != null, "[entityClass] must not be null");
        this.entityClass = entityClass;
    }

    @VisibleForTesting
    void setEntityManager(EntityManager entityManager)
    {
        checkArgument(this.entityClass != null, "[entityManager] must not be null");
        this.entityManager = entityManager;
    }

    public E findById(I id)
    {
        checkArgument(id != null, "[id] must not be null");
        return this.entityManager.find(this.entityClass, id.getId());
    }

    public Collection<E> findAll()
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(this.entityClass);
        query.select(query.from(this.entityClass));
        return this.entityManager.createQuery(query).getResultList();
    }

    public void persist(E entity)
    {
        checkArgument(entity != null, "[entity] must not be null");
        this.entityManager.persist(entity);
    }
}
