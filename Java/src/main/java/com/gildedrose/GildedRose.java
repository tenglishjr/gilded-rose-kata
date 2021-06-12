package com.gildedrose;

class GildedRose {

    Item[] items;
    ItemUpdaterFactory factory = new ItemUpdaterFactory();

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateAtEndOfDay() {
        for ( Item item : items ) {
            ItemUpdater itemUpdater = createItemUpdater(item.name);
            itemUpdater.updateQuality(item);
            itemUpdater.updateSellIn(item);
        }
    }

    private ItemUpdater createItemUpdater(String itemName) {
        return factory.createItemUpdater(itemName);
    }
}
