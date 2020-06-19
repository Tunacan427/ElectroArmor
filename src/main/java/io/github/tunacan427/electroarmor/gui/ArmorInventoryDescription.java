package io.github.tunacan427.electroarmor.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.tunacan427.electroarmor.item.ModItems;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class ArmorInventoryDescription extends SyncedGuiDescription {
    public ArmorInventoryDescription(int syncId, PlayerInventory playerInventory, Inventory blockInventory, PropertyDelegate propertyDelegate) {
        super(ScreenHandlerType.GENERIC_9X1, syncId, playerInventory, blockInventory, propertyDelegate);

        WGridPanel root = new WGridPanel(1);
        setRootPanel(root);
        root.setSize(160, 140);

        WLabel guiLabel = new WLabel(new TranslatableText("container.electroarmor.armorInventory"));
        root.add(guiLabel, 3, 0);

        WSprite spriteHelmet = new WSprite(new Identifier("textures/item/empty_armor_slot_helmet.png"));
        WSprite spriteChestplate = new WSprite(new Identifier("textures/item/empty_armor_slot_chestplate.png"));
        WSprite spriteLeggings = new WSprite(new Identifier("textures/item/empty_armor_slot_leggings.png"));
        WSprite spriteBoots = new WSprite(new Identifier("textures/item/empty_armor_slot_boots.png"));

        WItemSlot[] slots = new WItemSlot[8];
        playerInventory.armor.forEach(stack -> {
            if (stack.getItem() == ModItems.ELECTRONIC_HELMET) {
                slots[0] = WItemSlot.of(blockInventory, 0);
                slots[1] = WItemSlot.of(blockInventory, 1);
                root.add(spriteHelmet, 10, 96, 16, 16);
            }
            if (stack.getItem() == ModItems.ELECTRONIC_CHESTPLATE) {
                slots[2] = WItemSlot.of(blockInventory, 2);
                slots[3] = WItemSlot.of(blockInventory, 3);
                root.add(spriteChestplate, 54, 96, 16, 16);
            }
            if (stack.getItem() == ModItems.ELECTRONIC_LEGGINGS) {
                slots[4] = WItemSlot.of(blockInventory, 4);
                slots[5] = WItemSlot.of(blockInventory, 5);
                root.add(spriteLeggings, 98, 96, 16, 16);
            }
            if (stack.getItem() == ModItems.ELECTRONIC_BOOTS) {
                slots[6] = WItemSlot.of(blockInventory, 6);
                slots[7] = WItemSlot.of(blockInventory, 7);
                root.add(spriteBoots, 142, 96, 16, 16);
            }
        });
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                root.add(slots[i], (i * 18) + (int)Math.floor((double)i / 2.0d) * 8,72);
            }
        }

        root.add(this.createPlayerInventoryPanel(), 3, 130);

        root.validate(this);
    }
}
