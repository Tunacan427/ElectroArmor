package io.github.tunacan427.electroarmor.inventory;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface InventoryComponent extends Component {
    DefaultedList<ItemStack> getItems();
    Inventory asInventory();
}
