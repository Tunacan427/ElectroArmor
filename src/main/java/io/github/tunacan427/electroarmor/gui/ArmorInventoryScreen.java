package io.github.tunacan427.electroarmor.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;

public class ArmorInventoryScreen extends CottonInventoryScreen<ArmorInventoryDescription> {
    public ArmorInventoryScreen(ArmorInventoryDescription description, PlayerEntity player) {
        super(description, player);
    }
}
