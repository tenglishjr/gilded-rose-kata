package com.gildedrose;

import static sun.swing.MenuItemLayoutHelper.max;

public class DefaultUpdater implements ItemUpdater {

    @Override
    public void updateSellIn(Item item) {
            item.sellIn--;
    }

    @Override
    public void updateQuality(Item item) {
        item.quality = decreaseQuality(item.quality, item.sellIn);
    }

    private int decreaseQuality(int quality, int sellIn) {
        return max(quality - getQualityDecrement(sellIn), 0);
    }

    private int getQualityDecrement(int sellIn) {
        return sellDateHasPassed(sellIn) ? 2 : 1;
    }

    protected boolean sellDateHasPassed(int sellIn) {
        return sellIn <= 0;
    }
}
