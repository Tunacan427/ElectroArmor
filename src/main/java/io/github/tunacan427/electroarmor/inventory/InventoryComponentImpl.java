package io.github.tunacan427.electroarmor.inventory;

import io.github.tunacan427.electroarmor.item.module.ModuleItem;
import jdk.nashorn.internal.ir.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Nameable;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class InventoryComponentImpl implements ImplementedInventory, InventoryComponent, Nameable {
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

    @Override
    public Text getName() {
        return new TranslatableText("container.electroarmor.armorInventory");
    }
}
