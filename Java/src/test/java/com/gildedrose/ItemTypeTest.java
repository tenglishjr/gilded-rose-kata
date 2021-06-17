package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemType.AGED_BRIE;
import static com.gildedrose.ItemType.BACKSTAGE_PASSES;
import static com.gildedrose.ItemType.CONJURED;
import static com.gildedrose.ItemType.SULFURAS;
import static com.gildedrose.ItemType.UNKNOWN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ItemTypeTest {

    @Test
    public void createsUnknownTypeForUnknownItemName() {
        assertEquals(UNKNOWN, ItemType.forName("foo"));
    }

    @Test
    public void createsDefaultUpdaterForUnknownType() {
        assertSame(UNKNOWN.createItemUpdater().getClass(), DefaultUpdater.class);
    }

    @Test
    public void createsAgedBrieUpdaterForAgedBrieType() {
        assertSame(AGED_BRIE.createItemUpdater().getClass(), AgedBrieUpdater.class);
    }

    @Test
    public void createsSulfurasUpdaterForSulfurasType() {
        assertSame(SULFURAS.createItemUpdater().getClass(), SulfurasUpdater.class);
    }

    @Test
    public void createsBackstagePassesUpdaterForBackstagePassesType() {
        assertSame(BACKSTAGE_PASSES.createItemUpdater().getClass(), BackstagePassesUpdater.class);
    }

    @Test
    public void createsConjuredUpdaterForConjuredType() {
        assertSame(CONJURED.createItemUpdater().getClass(), ConjuredUpdater.class);
    }
}
