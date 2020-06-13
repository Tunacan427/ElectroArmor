package io.github.tunacan427.electroarmor.client;

import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.gui.ArmorInventoryDescription;
import io.github.tunacan427.electroarmor.gui.ArmorInventoryScreen;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ElectroArmorClient implements ClientModInitializer {
    private static FabricKeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(ElectroArmor.id("armor_inventory"), (syncId, identifier, player, buf) ->
                new ArmorInventoryScreen(new ArmorInventoryDescription(syncId, player.inventory, ElectroArmor.ARMOR_INVENTORY.get(player).asInventory(), null), player));
        keyBinding = FabricKeyBinding.Builder.create(
                ElectroArmor.id("armor_inventory_key_binding"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                "Electro Armor Keybinds"
        ).build();
        KeyBindingRegistry.INSTANCE.addCategory("Electro Armor Keybinds");
        KeyBindingRegistry.INSTANCE.register(keyBinding);
        ClientTickCallback.EVENT.register(e -> {
            if (keyBinding.isPressed()) {
                ClientSidePacketRegistry.INSTANCE.sendToServer(ElectroArmor.id("armor_inventory_packet"), new PacketByteBuf(Unpooled.buffer()));
            }
        });
    }
}
