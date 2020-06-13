package io.github.tunacan427.electroarmor.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class ArmorInventoryDescription extends SyncedGuiDescription {
    public ArmorInventoryDescription(int syncId, PlayerInventory playerInventory, Inventory blockInventory, PropertyDelegate propertyDelegate) {
        super(syncId, playerInventory, blockInventory, propertyDelegate);

        WGridPanel root = new WGridPanel(1);
        setRootPanel(root);
        root.setSize(160, 100);

        WItemSlot slots[] = new WItemSlot[8];
        slots[0] = WItemSlot.of(blockInventory, 0);
        slots[1] = WItemSlot.of(blockInventory, 1);
        slots[2] = WItemSlot.of(blockInventory, 2);
        slots[3] = WItemSlot.of(blockInventory, 3);
        slots[4] = WItemSlot.of(blockInventory, 4);
        slots[5] = WItemSlot.of(blockInventory, 5);
        slots[6] = WItemSlot.of(blockInventory, 6);
        slots[7] = WItemSlot.of(blockInventory, 7);
        for (int i = 0; i < slots.length; i++) {
            root.add(slots[i], (i * 18) + (int)Math.floor((double)i / 2.0d) * 8,36);
        }

        WSprite spriteHelmet = new WSprite(new Identifier("textures/item/empty_armor_slot_helmet.png"));
        root.add(spriteHelmet, 10, 60, 16, 16);
        WSprite spriteChestplate = new WSprite(new Identifier("textures/item/empty_armor_slot_chestplate.png"));
        root.add(spriteChestplate, 54, 60, 16, 16);
        WSprite spriteLeggings = new WSprite(new Identifier("textures/item/empty_armor_slot_leggings.png"));
        root.add(spriteLeggings, 98, 60, 16, 16);
        WSprite spriteBoots = new WSprite(new Identifier("textures/item/empty_armor_slot_boots.png"));
        root.add(spriteBoots, 142, 60, 16, 16);

        root.add(this.createPlayerInventoryPanel(), 3, 90);

        root.validate(this);
    }
}
