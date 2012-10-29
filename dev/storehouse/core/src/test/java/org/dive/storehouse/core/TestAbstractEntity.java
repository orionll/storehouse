package org.dive.storehouse.core;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

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

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private EntityId entityId1;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private EntityId entityId2;

    @BeforeMethod
    public void prepare()
    {
        initMocks(this);
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
        doReturn(this.entityId1).when(this.entity1).getId();
        doReturn(null).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isNotEqualTo(this.entity2);
        assertThat(this.entity2).isNotEqualTo(this.entity1);
    }

    @Test
    public void testEqualsPersistedItemsWithEqualIds()
    {
        // Given
        this.entityId1.setId(100);
        this.entityId2.setId(100);
        doReturn(this.entityId1).when(this.entity1).getId();
        doReturn(this.entityId2).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isEqualTo(this.entity2);
        assertThat(this.entity2).isEqualTo(this.entity1);
    }

    @Test
    public void testEqualsPersistedItemsWithDifferentIds()
    {
        // Given
        this.entityId1.setId(100);
        this.entityId2.setId(200);
        doReturn(this.entityId1).when(this.entity1).getId();
        doReturn(this.entityId2).when(this.entity2).getId();

        // Then
        assertThat(this.entity1).isNotEqualTo(this.entity2);
        assertThat(this.entity2).isNotEqualTo(this.entity1);
    }
}
