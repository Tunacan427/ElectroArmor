package io.github.tunacan427.electroarmor.item;

import net.minecraft.item.Item;

public class HammerItem extends Item {
    public HammerItem() {
        super(ModItems.defaults().maxCount(1));
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public Item getRecipeRemainder() {
        return this;
    }
}
