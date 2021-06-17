package com.gildedrose;

public class BackstagePassesUpdater extends DefaultUpdater {

    @Override
    public void updateQuality(Item item) {
        item.quality = sellDateHasPassed(item.sellIn) ? 0 : increaseQuality(item.quality, item.sellIn);
    }

    private int increaseQuality(int quality, int sellIn) {
        if (sellIn <= 5 && quality <= (MAX_QUALITY - 3))
            return quality + 3;
        else if (sellIn <= 10 && quality <= (MAX_QUALITY - 2))
            return quality + 2;
        else
            return (quality < MAX_QUALITY) ? quality + 1 : quality;
    }
}
