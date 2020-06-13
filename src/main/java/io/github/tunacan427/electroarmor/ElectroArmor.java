package io.github.tunacan427.electroarmor;

import io.github.tunacan427.electroarmor.energy.EnergyComponent;
import io.github.tunacan427.electroarmor.energy.EnergyComponentImpl;
import io.github.tunacan427.electroarmor.gui.ArmorInventoryDescription;
import io.github.tunacan427.electroarmor.inventory.InventoryComponent;
import io.github.tunacan427.electroarmor.inventory.InventoryComponentImpl;
import io.github.tunacan427.electroarmor.item.ModItems;
import io.github.tunacan427.electroarmor.item.module.ModuleItem;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElectroArmor implements ModInitializer {
    public static final String MOD_ID = "electroarmor";

    public static final Logger LOGGER = LogManager.getLogger("Electro Armor");

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(id("item_group"))
            .icon(() -> new ItemStack(ModItems.ELECTRONIC_CHESTPLATE)).build();

    public static final ComponentType<EnergyComponent> ENERGY =
            ComponentRegistry.INSTANCE.registerIfAbsent(id("energy"), EnergyComponent.class);

    public static final ComponentType<InventoryComponent> ARMOR_INVENTORY =
            ComponentRegistry.INSTANCE.registerIfAbsent(id("armor_inventory"), InventoryComponent.class);

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        AutoConfig.register(ElectroArmorConfig.class, GsonConfigSerializer::new);
        EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> {
            components.put(ENERGY, new EnergyComponentImpl());
            components.put(ARMOR_INVENTORY, new InventoryComponentImpl());
            ServerTickCallback.EVENT.register(ticks -> {
                components.get(ARMOR_INVENTORY).getItems().forEach(stack -> {
                    Item item = stack.getItem();
                    if (item instanceof ModuleItem) {
                        ((ModuleItem) item).tick(player);
                    }
                });
            });
        });
        EntityComponents.setRespawnCopyStrategy(ENERGY, RespawnCopyStrategy.INVENTORY);
        ContainerProviderRegistry.INSTANCE.registerFactory(id("armor_inventory"), (syncId, id, player, buf)
                -> new ArmorInventoryDescription(syncId, player.inventory, ARMOR_INVENTORY.get(player).asInventory(), null));
        ModItems.registerItems();
        ServerSidePacketRegistry.INSTANCE.register(id("armor_inventory_packet"), (packetContext, attachedData) -> packetContext.getTaskQueue().execute(() -> {
            ContainerProviderRegistry.INSTANCE.openContainer(id("armor_inventory"), packetContext.getPlayer(), (packetByteBuf -> packetByteBuf.writeBlockPos(packetContext.getPlayer().getBlockPos())));
        }));
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }
}
