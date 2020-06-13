package io.github.tunacan427.electroarmor.item.module;

import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public abstract class ModuleItem extends Item {
    public final int tier;

    public ModuleItem(int tier) {
        super(ModItems.defaults().maxCount(1));
        this.tier = tier;
    }

    public abstract void tick(PlayerEntity player);
}
