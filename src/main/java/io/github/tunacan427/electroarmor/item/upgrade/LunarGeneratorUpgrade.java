package io.github.tunacan427.electroarmor.item.upgrade;

import io.github.tunacan427.electroarmor.energy.EnergyComponent;
import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LunarGeneratorUpgrade extends UpgradeItem {
    public LunarGeneratorUpgrade(int tier) {
        super(tier);
    }

    public int getGenerationRate() {
        switch(tier) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
        }
        return 0;
    }

    @Override
    public void tick(PlayerEntity player, World world, EnergyComponent energy) {
        if (world != null && world.getDimension().hasSkyLight() && world.isSkyVisible(player.getBlockPos().up()) && !world.isRaining() && !world.isThundering() && world.isNight())
            energy.incrementValue(getGenerationRate());
    }

    @Override
    public Item getArmorPiece() {
        return ModItems.ELECTRONIC_HELMET;
    }
}
