package org.dive.storehouse.core;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

import java.util.Objects;

import org.mockito.Answers;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created 30.10.2012
 * @author orionll
 *
**/
public class TestAbstractEntity
{
    // Testing abstract classes
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private AbstractEntity entity1;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private AbstractEntity entity2;

    @BeforeMethod
    public void prepare()
    {
        initMocks(this);
    }

    @Test
    public void testEqualsNull()
    {
        assertThat(this.entity1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsDifferentClass()
    {
        assertThat(this.entity1).isNotEqualTo(100);
    }

    @Test
    public void testEqualsNotPersistedEntities()
    {
        // Given
        doReturn(null).when(this.entity1).getId();
        doReturn(null).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isEqualTo(this.entity1);
        assertThat(this.entity2).isEqualTo(this.entity2);
        assertThat(this.entity1).isNotEqualTo(this.entity2);
        assertThat(this.entity2).isNotEqualTo(this.entity1);
    }

    @Test
    public void testEqualsOnePersistedEntityOtherNotPersisted()
    {
        // Given
        EntityId entityId1 = new MockEntityId(1);
        doReturn(entityId1).when(this.entity1).getId();
        doReturn(null).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isNotEqualTo(this.entity2);
        assertThat(this.entity2).isNotEqualTo(this.entity1);
    }

    @Test
    public void testEqualsPersistedItemsWithEqualIds()
    {
        // Given
        EntityId entityId1 = new MockEntityId(100);
        EntityId entityId2 = new MockEntityId(100);
        doReturn(entityId1).when(this.entity1).getId();
        doReturn(entityId2).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isEqualTo(this.entity2);
        assertThat(this.entity2).isEqualTo(this.entity1);
    }

    @Test
    public void testEqualsPersistedItemsWithDifferentIds()
    {
        // Given
        EntityId entityId1 = new MockEntityId(100);
        EntityId entityId2 = new MockEntityId(200);
        doReturn(entityId1).when(this.entity1).getId();
        doReturn(entityId2).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isNotEqualTo(this.entity2);
        assertThat(this.entity2).isNotEqualTo(this.entity1);
    }

    @Test
    public void testHashCode()
    {
        // Given
        EntityId entityId = new MockEntityId(100);
        doReturn(entityId).when(this.entity1).getId();

        //Then
        assertThat(this.entity1.hashCode()).isEqualTo(Objects.hash(entityId));
    }

    private static class MockEntityId extends EntityId
    {
        private MockEntityId(long id)
        {
            super(id);
        }
    }
}
