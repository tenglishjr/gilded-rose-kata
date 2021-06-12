package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemType.CONJURED;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ItemUpdaterFactoryTest {

    private ItemUpdaterFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new ItemUpdaterFactory();
    }

    @Test
    public void createsDefaultUpdaterForUnknownItem() {
        assertSame(factory.createItemUpdater("foo").getClass(), DefaultUpdater.class);
    }

    @Test
    public void createsConjuredUpdaterForConjuredItem() {
        assertSame(factory.createItemUpdater(CONJURED.name).getClass(), ConjuredUpdater.class);
    }

}
