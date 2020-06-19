package io.github.tunacan427.electroarmor.client;

import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.energy.EnergyComponentImpl;
import io.github.tunacan427.electroarmor.gui.ArmorInventoryDescription;
import io.github.tunacan427.electroarmor.gui.ArmorInventoryScreen;
import io.github.tunacan427.electroarmor.item.ModItems;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ElectroArmorClient implements ClientModInitializer {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    private static FabricKeyBinding keyBinding;

    private boolean wearingElectroArmor = false;

    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(ElectroArmor.ARMOR_INVENTORY_CONTAINER, (syncId, identifier, player, buf) ->
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
            if (mc.player != null) {
                final PlayerEntity player = mc.player;
                if (keyBinding.isPressed() && wearingElectroArmor) {
                    ClientSidePacketRegistry.INSTANCE.sendToServer(ElectroArmor.ARMOR_INVENTORY_PACKET, new PacketByteBuf(Unpooled.buffer()));
                }
                if (mc.player.clientWorld != null && mc.player.clientWorld.getTime() % 20 == 0) {
                    wearingElectroArmor = player.inventory.armor.stream().anyMatch(stack -> {
                        Item item = stack.getItem();
                        return item == ModItems.ELECTRONIC_HELMET || item == ModItems.ELECTRONIC_CHESTPLATE || item == ModItems.ELECTRONIC_LEGGINGS || item == ModItems.ELECTRONIC_BOOTS;
                    });
                }
            }
        });
        final TranslatableText energyStatusString = new TranslatableText("text.electroarmor.energyStatus");
        HudRenderCallback.EVENT.register((matrixStack, delta) -> {
            if (mc.player != null && !mc.options.hudHidden && !mc.options.debugEnabled && (mc.currentScreen == null || mc.currentScreen instanceof ChatScreen)) {
                final TextRenderer textRenderer = mc.textRenderer;
                if (wearingElectroArmor) {
                    Text text = energyStatusString.shallowCopy().append(ElectroArmor.ENERGY.get(mc.player).getValue() + "/" + EnergyComponentImpl.MAX_ENERGY);
                    textRenderer.drawWithShadow(matrixStack, text, mc.getWindow().getScaledWidth() - 3 - textRenderer.getWidth(text), 3, 0xFFFFFF);
                }
            }
        });
    }
}
