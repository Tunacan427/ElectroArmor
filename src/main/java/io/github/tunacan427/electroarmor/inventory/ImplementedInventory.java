package io.github.tunacan427.electroarmor.inventory;

import io.github.tunacan427.electroarmor.item.ModItems;
import io.github.tunacan427.electroarmor.item.upgrade.LunarGeneratorUpgrade;
import io.github.tunacan427.electroarmor.item.upgrade.SolarGeneratorUpgrade;
import io.github.tunacan427.electroarmor.item.upgrade.SolunarGeneratorUpgrade;
import io.github.tunacan427.electroarmor.item.upgrade.UpgradeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * A simple {@code Inventory} implementation with only default methods + an item list getter.
 *
 * Originally by Juuz
 */
public interface ImplementedInventory extends Inventory {
    /**
     * Gets the item list of this inventory.
     * Must return the same instance every time it's called.
     */
    DefaultedList<ItemStack> getItems();
    // Creation
    /**
     * Creates an inventory from the item list.
     */
    static ImplementedInventory of(DefaultedList<ItemStack> items) {
        return () -> items;
    }
    /**
     * Creates a new inventory with the size.
     */
    static ImplementedInventory ofSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }
    // Inventory
    /**
     * Returns the inventory size.
     */
    @Override
    default int size() {
        return getItems().size();
    }
    /**
     * @return true if this inventory has only empty stacks, false otherwise
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    /**
     * Gets the item in the slot.
     */
    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }
    /**
     * Takes a stack of the size from the slot.
     * <p>(default implementation) If there are less items in the slot than what are requested,
     * takes all items in that slot.
     */
    @Override
    default ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }
    /**
     * Removes the current stack in the {@code slot} and returns it.
     */
    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }
    /**
     * Replaces the current stack in the {@code slot} with the provided stack.
     * <p>If the stack is too big for this inventory ({@link Inventory#getMaxCountPerStack()}),
     * it gets resized to this inventory's maximum amount.
     */
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }
    /**
     * Clears {@linkplain #getItems() the item list}}.
     */
    @Override
    default void clear() {
        getItems().clear();
    }
    @Override
    default void markDirty() {
        // Override if you want behavior.
    }
    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    default boolean isValid(int slot, ItemStack stack) {
        Item targetItem = stack.getItem();
        if (stack.getItem() instanceof UpgradeItem) {
            for (ItemStack itemStack : getItems()) {
                Item item = itemStack.getItem();
                if (targetItem instanceof SolarGeneratorUpgrade || targetItem instanceof LunarGeneratorUpgrade || targetItem instanceof SolunarGeneratorUpgrade) {
                    if (item instanceof SolarGeneratorUpgrade || item instanceof LunarGeneratorUpgrade || item instanceof SolunarGeneratorUpgrade) {
                        return false;
                    }
                }
                if (item.getClass().equals(targetItem.getClass())) {
                    return false;
                }
            }
            if ((slot == 0 || slot == 1) && ((UpgradeItem) targetItem).getArmorPiece() == ModItems.ELECTRONIC_HELMET)
                return true;
            if ((slot == 2 || slot == 3) && ((UpgradeItem) targetItem).getArmorPiece() == ModItems.ELECTRONIC_CHESTPLATE)
                return true;
            if ((slot == 4 || slot == 5) && ((UpgradeItem) targetItem).getArmorPiece() == ModItems.ELECTRONIC_LEGGINGS)
                return true;
            if ((slot == 6 || slot == 7) && ((UpgradeItem) targetItem).getArmorPiece() == ModItems.ELECTRONIC_BOOTS)
                return true;
            return false;
        }
        return false;
    }

    @Override
    default int getMaxCountPerStack() {
        return 1;
    }
}