package io.github.tunacan427.electroarmor.integration;

import io.github.prospector.modmenu.api.ModMenuApi;
import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.ElectroArmorConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

import java.util.Optional;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return ElectroArmor.MOD_ID;
    }

    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(AutoConfig.getConfigScreen(ElectroArmorConfig.class, screen));
    }
}
