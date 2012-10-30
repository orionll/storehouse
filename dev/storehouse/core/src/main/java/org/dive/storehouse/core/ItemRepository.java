package org.dive.storehouse.core;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created 29.10.2012
 * @author orionll
 * 
**/
@Stateless
public class ItemRepository extends Repository<Item, ItemId>
{
    @PersistenceContext
    private EntityManager entityManager;

    public ItemRepository()
    {
        super(Item.class);
    }

    public Collection<Item> findItems(String searchExpression)
    {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);

        Root<Item> from = query.from(Item.class);

        query.select(from);

        Predicate nameLike = builder.like(from.<String> get("name"), searchExpression);
        Predicate descriptionLike = builder.like(from.<String> get("description"), searchExpression);

        query.where(builder.or(nameLike, descriptionLike));

        return this.entityManager.createQuery(query).getResultList();
    }
}
