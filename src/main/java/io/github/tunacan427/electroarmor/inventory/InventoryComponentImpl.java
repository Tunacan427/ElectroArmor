package io.github.tunacan427.electroarmor.inventory;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public class InventoryComponentImpl implements ImplementedInventory, InventoryComponent {
    private DefaultedList<ItemStack> items = DefaultedList.ofSize(8, ItemStack.EMPTY);

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public Inventory asInventory() {
        return ImplementedInventory.of(items);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return Inventories.toTag(tag, items);
    }

    public void vanishCursedItems() {
        items.forEach(itemStack -> {
            if (!itemStack.isEmpty() && EnchantmentHelper.hasVanishingCurse(itemStack)) {
                items.set(items.indexOf(itemStack), ItemStack.EMPTY);
            }
        });
    }

    public void dropAll(PlayerEntity player) {
        items.forEach(itemStack -> {
            if (!itemStack.isEmpty()) {
                player.dropItem(itemStack, true, false);
                items.set(items.indexOf(itemStack), ItemStack.EMPTY);
            }
        });
    }
}
