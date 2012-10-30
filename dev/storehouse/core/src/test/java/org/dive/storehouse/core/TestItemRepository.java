package org.dive.storehouse.core;

import static org.mockito.MockitoAnnotations.*;

import javax.persistence.EntityManager;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

/**
 * Created 29.10.2012
 * @author orionll
 *
**/

public class TestItemRepository
{
    private ItemRepository itemRepository = new ItemRepository();

    @Mock
    private EntityManager entityManager;

    @BeforeMethod
    public void prepare()
    {
        initMocks(this);
        this.itemRepository.setEntityManager(this.entityManager);
    }
}
