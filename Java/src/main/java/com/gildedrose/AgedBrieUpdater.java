package com.gildedrose;

import static java.lang.Math.min;

public class AgedBrieUpdater extends DefaultUpdater {

    @Override
    public void updateQuality(Item item) {
        item.quality = increaseQuality(item.quality, item.sellIn);
    }

    private int increaseQuality(int quality, int sellIn) {
        quality = quality + (sellDateHasPassed(sellIn) ? 2 : 1);

        return min(quality, MAX_QUALITY);
    }
}
