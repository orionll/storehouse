package org.dive.storehouse.core;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mockito.Answers;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

/**
 * Created 30.10.2012
 * @author orionll
 *
**/
public class TestRepository
{
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Repository<AbstractEntity, EntityId> repository;

    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaBuilder builder;
    @Mock
    private CriteriaQuery<AbstractEntity> query;
    @Mock
    private Root<AbstractEntity> root;
    @Mock
    private TypedQuery<AbstractEntity> typedQuery;
    @Mock
    private AbstractEntity entity;
    @Mock
    private EntityId entityId;

    @BeforeMethod
    public void prepare()
    {
        initMocks(this);
        this.repository.setEntityClass(AbstractEntity.class);
        this.repository.setEntityManager(this.entityManager);
    }

    @Test
    public void testFindAll()
    {
        // Given
        when(this.entityManager.getCriteriaBuilder()).thenReturn(this.builder);
        when(this.builder.createQuery(AbstractEntity.class)).thenReturn(this.query);
        when(this.query.from(AbstractEntity.class)).thenReturn(this.root);
        when(this.entityManager.createQuery(this.query)).thenReturn(this.typedQuery);
        when(this.typedQuery.getResultList()).thenReturn(ImmutableList.of(this.entity));

        // When
        Collection<AbstractEntity> returnedList = this.repository.findAll();

        // Then
        verify(this.entityManager).getCriteriaBuilder();
        verify(this.builder).createQuery(AbstractEntity.class);
        verify(this.query).from(AbstractEntity.class);
        verify(this.query).select(this.root);
        verify(this.entityManager).createQuery(this.query);
        verify(this.typedQuery).getResultList();
        assertThat(returnedList).isEqualTo(ImmutableList.of(this.entity));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByNullId()
    {
        // When
        this.repository.findById(null);
    }

    @Test
    public void testFindByNotNullId()
    {
        // Given
        when(this.entityManager.find(AbstractEntity.class, 100L)).thenReturn(this.entity);
        when(this.entityId.getId()).thenReturn(100L);

        // When
        AbstractEntity foundEntity = this.repository.findById(this.entityId);

        // Then
        verify(this.entityManager, only()).find(AbstractEntity.class, 100L);
        assertThat(foundEntity).isEqualTo(this.entity);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testPersistNullEntity()
    {
        // When
        this.repository.persist(null);
    }

    @Test
    public void testPersistNotNullEntity()
    {
        // When
        this.repository.persist(this.entity);

        // Then
        verify(this.entityManager, only()).persist(this.entity);
    }
}
