package io.github.tunacan427.electroarmor.item.upgrade;

import io.github.tunacan427.electroarmor.energy.EnergyComponent;
import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SolunarGeneratorUpgrade extends UpgradeItem {
    public SolunarGeneratorUpgrade(int tier) {
        super(tier);
    }

    @Override
    public void tick(PlayerEntity player, World world, EnergyComponent energy) {
        if (world != null && world.getDimension().hasSkyLight() && world.isSkyVisible(player.getBlockPos().up()) && !world.isRaining() && !world.isThundering())
            energy.incrementValue(3);
    }

    @Override
    public Item getArmorPiece() {
        return ModItems.ELECTRONIC_HELMET;
    }
}
