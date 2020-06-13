package io.github.tunacan427.electroarmor;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = ElectroArmor.MOD_ID)
public class ElectroArmorConfig implements ConfigData {
    public static ElectroArmorConfig get() {
        return AutoConfig.getConfigHolder(ElectroArmorConfig.class).getConfig();
    }

    public int placeholderInt = 10;
}
