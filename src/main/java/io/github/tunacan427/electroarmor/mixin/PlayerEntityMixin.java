package io.github.tunacan427.electroarmor.mixin;

import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.inventory.InventoryComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(at = @At("RETURN"), method = "dropInventory()V")
    protected void dropInventory(CallbackInfo info) {
        if (!((PlayerEntity)(Object) this).world.getGameRules().getBoolean(GameRules.field_19389)) {
            InventoryComponent inventory = ElectroArmor.ARMOR_INVENTORY.get((PlayerEntity)(Object) this);
            inventory.vanishCursedItems();
            inventory.dropAll((PlayerEntity)(Object) this);
        }
    }
}
