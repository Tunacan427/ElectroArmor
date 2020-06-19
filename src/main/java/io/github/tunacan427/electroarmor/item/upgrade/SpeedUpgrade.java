package io.github.tunacan427.electroarmor.item.upgrade;

import io.github.tunacan427.electroarmor.energy.EnergyComponent;
import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SpeedUpgrade extends UpgradeItem {
    public SpeedUpgrade(int tier) {
        super(tier);
    }

    @Override
    public void tick(PlayerEntity player, World world, EnergyComponent energy) {
        if (energy.decrementValue(4)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1));
        }
    }

    @Override
    public Item getArmorPiece() {
        return ModItems.ELECTRONIC_LEGGINGS;
    }
}
