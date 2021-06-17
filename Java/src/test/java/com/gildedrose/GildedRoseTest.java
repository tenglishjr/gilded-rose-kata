package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private GildedRose app;
    private static final String ARBIT_NAME = "foo";
    private static final int ARBIT_SELLIN = 18;
    private static final int ARBIT_QUALITY = 20;

    @Test
    public void itemHasSpecifiedName() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        assertEquals(ARBIT_NAME, getFirstItem().name);
    }

    @Test
    public void itemHasSpecifiedSellIn() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        assertEquals(ARBIT_SELLIN, getFirstItem().sellIn);
    }

    @Test
    public void itemHasSpecifiedQuality() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        assertEquals(ARBIT_QUALITY, getFirstItem().quality);
    }

    @Test
    public void nameIsUnchangedAtEOD() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_NAME, getFirstItem().name);
    }

    @Test
    public void sellInDecreasesAtEOD() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_SELLIN - 1, getFirstItem().sellIn);
    }

    @Test
    public void qualityDecreasesByOneAtEOD() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY - 1, getFirstItem().quality);
    }

    @Test
    public void qualityDecreasesByTwoAtEODIfSellDateIsPassed () {
        app = createAppWithOneItem(ARBIT_NAME, 0, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY - 2, getFirstItem().quality);
    }

    @Test
    public void qualityIsNeverLessThanZero() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, 0);
        app.updateAtEndOfDay();
        assertEquals(0, getFirstItem().quality);
    }

    @Test
    public void agedBrieQualityIncreases() {
        app = createAppWithOneItem(AGED_BRIE.name, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY + 1, getFirstItem().quality);
    }

    @Test
    public void qualityIsNeverGreaterThanFifty() {
        app = createAppWithOneItem(AGED_BRIE.name, ARBIT_SELLIN, 50);
        app.updateAtEndOfDay();
        assertEquals(50, getFirstItem().quality);
    }

    @Test
    public void sulfurasNeverHasToBeSold() {
        app = createAppWithOneItem(SULFURAS.name, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_SELLIN, getFirstItem().sellIn);
    }

    @Test
    public void backstagePassesQualityIncreaseBy1IfSellDateIsGreaterThan10() {
        app = createAppWithOneItem(BACKSTAGE_PASSES.name, 11, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY + 1, getFirstItem().quality);
    }

    @Test
    public void backstagePassesQualityIncreaseBy2IfSellDateIs10OrLess() {
        app = createAppWithOneItem(BACKSTAGE_PASSES.name, 10, ARBIT_QUALITY);
        System.out.printf("Backstage pass info\tSellIn: %d\tQuality: %d\n", getFirstItem().sellIn, getFirstItem().quality);
        app.updateAtEndOfDay();
        System.out.printf("Backstage pass info\tSellIn: %d\tQuality: %d\n", getFirstItem().sellIn, getFirstItem().quality);
        assertEquals(ARBIT_QUALITY + 2, getFirstItem().quality);
    }

    @Test
    public void backstagePassesQualityIncreaseBy3IfSellDateIs5OrLess() {
        app = createAppWithOneItem(BACKSTAGE_PASSES.name, 5, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY + 3, getFirstItem().quality);
    }

    @Test
    public void backstagePassesQualityIsZeroIfSellDateIsZero() {
        app = createAppWithOneItem(BACKSTAGE_PASSES.name, 0, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(0, getFirstItem().quality);
    }

    @Test
    public void sulfurasMaintainsQuality() {
        app = createAppWithOneItem(SULFURAS.name, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY, getFirstItem().quality);
    }

    @Test
    public void agedBrieQualityIncreasesBy2WhenSellDateIsPassed() {
        app = createAppWithOneItem(AGED_BRIE.name, 0, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY + 2, getFirstItem().quality);
    }

    @Test
    public void updatesSellInForAllItemsAtEOD() {
        Item agedBrieItem = new Item(AGED_BRIE.name, ARBIT_SELLIN, ARBIT_QUALITY);
        Item sulfurasItem = new Item(SULFURAS.name, ARBIT_SELLIN, ARBIT_QUALITY);
        GildedRose app = new GildedRose(new Item[] { agedBrieItem, sulfurasItem });

        app.updateAtEndOfDay();

        assertEquals(ARBIT_SELLIN - 1, app.items[0].sellIn);
        assertEquals(ARBIT_SELLIN, app.items[1].sellIn);
    }

    @Test
    public void updatesQualityForAllItemsAtEOD() {
        Item agedBrieItem = new Item(AGED_BRIE.name, ARBIT_SELLIN, ARBIT_QUALITY);
        Item backstageItem = new Item(BACKSTAGE_PASSES.name, 4, ARBIT_QUALITY);
        GildedRose app = new GildedRose(new Item[] { agedBrieItem, backstageItem });

        app.updateAtEndOfDay();

        assertEquals(ARBIT_QUALITY + 1, app.items[0].quality);
        assertEquals(ARBIT_QUALITY + 3, app.items[1].quality);
    }

    @Test
    public void conjuredItemQualityDecreasesBy2() {
        app = createAppWithOneItem(CONJURED.name, ARBIT_SELLIN, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY - 2, getFirstItem().quality);
    }

    @Test
    public void conjuredQualityDecreasesBy4OnceSellDateHasPassed() {
        app = createAppWithOneItem(CONJURED.name, 0, ARBIT_QUALITY);
        app.updateAtEndOfDay();
        assertEquals(ARBIT_QUALITY - 4, getFirstItem().quality);
    }

    @Test
    public void conjuredQualityIsNeverNegative() {
        app = createAppWithOneItem(CONJURED.name, ARBIT_SELLIN, 1);
        app.updateAtEndOfDay();
        assertEquals(0, getFirstItem().quality);
    }

    @Test
    public void conjuredQualityIsNeverNegativeEvenIfSellDateIsPassed() {
        app = createAppWithOneItem(CONJURED.name, 0, 3);
        app.updateAtEndOfDay();
        assertEquals(0, getFirstItem().quality);
    }

    @Test
    public void itemToStringContainsAllItemMetadata() {
        app = createAppWithOneItem(ARBIT_NAME, ARBIT_SELLIN, ARBIT_QUALITY);
        assertEquals(
            ARBIT_NAME + ", " + ARBIT_SELLIN + ", " + ARBIT_QUALITY,
            getFirstItem().toString()
        );
    }

    /************ Helper methods *************/

    private GildedRose createAppWithOneItem(String name, int sellIn, int quality) {
        return new GildedRose(createItemArrayWithSingleItem(name, sellIn, quality));
    }

    private Item[] createItemArrayWithSingleItem(String name, int sellIn, int quality) {
        return new Item[] { new Item(name, sellIn, quality) };
    }

    private Item getFirstItem() {
        return app.items[0];
    }
}
