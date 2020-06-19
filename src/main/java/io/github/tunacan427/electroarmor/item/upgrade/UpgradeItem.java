package io.github.tunacan427.electroarmor.item.upgrade;

import io.github.tunacan427.electroarmor.energy.EnergyComponent;
import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class UpgradeItem extends Item {
    public final int tier;

    public UpgradeItem(int tier) {
        super(ModItems.defaults().maxCount(1));
        this.tier = tier;
    }

    public abstract void tick(PlayerEntity player, World world, EnergyComponent energy);
    public abstract Item getArmorPiece();
}
