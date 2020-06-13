package io.github.tunacan427.electroarmor.item;

import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.item.armor.CustomArmorMaterial;
import io.github.tunacan427.electroarmor.item.module.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item IRON_PLATE = new Item(defaults());
    public static final Item CIRCUIT = new Item(defaults());
    public static final Item HAMMER = new Item(defaults());
    public static final Item BASE_MODULE = new Item(defaults());

    public static final Item ELECTRONIC_HELMET = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.HEAD, defaults());
    public static final Item ELECTRONIC_CHESTPLATE = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.CHEST, defaults());
    public static final Item ELECTRONIC_LEGGINGS = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.LEGS, defaults());
    public static final Item ELECTRONIC_BOOTS = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.FEET, defaults());

    public static final Item SOLAR_GENERATOR_MODULE_TIER_1 = new SolarGeneratorModule(1);
    public static final Item SOLAR_GENERATOR_MODULE_TIER_2 = new SolarGeneratorModule(2);
    public static final Item SOLAR_GENERATOR_MODULE_TIER_3 = new SolarGeneratorModule(3);
    public static final Item LUNAR_GENERATOR_MODULE_TIER_1 = new LunarGeneratorModule(1);
    public static final Item LUNAR_GENERATOR_MODULE_TIER_2 = new LunarGeneratorModule(2);
    public static final Item LUNAR_GENERATOR_MODULE_TIER_3 = new LunarGeneratorModule(3);
    public static final Item BURNABLE_GENERATOR_MODULE = new BurnableGeneratorModule(1);
    public static final Item SOLUNAR_GENERATOR_MODULE = new SolunarGeneratorModule(1);
    public static final Item ATTACKER_MODULE_TIER_1 = new AttackerModule(1);
    public static final Item ATTACKER_MODULE_TIER_2 = new AttackerModule(2);
    public static final Item ATTACKER_MODULE_TIER_3 = new AttackerModule(3);
    public static final Item MINING_MODULE_TIER_1 = new MiningModule(1);
    public static final Item MINING_MODULE_TIER_2 = new MiningModule(2);
    public static final Item FEEDING_MODULE = new FeedingModule(1);
    public static final Item SPEED_MODULE = new SpeedModule(1);

    public static void registerItems() {
        register("iron_plate", IRON_PLATE);
        register("circuit", CIRCUIT);
        register("hammer", HAMMER);
        register("base_module", BASE_MODULE);

        register("electronic_helmet", ELECTRONIC_HELMET);
        register("electronic_chestplate", ELECTRONIC_CHESTPLATE);
        register("electronic_leggings", ELECTRONIC_LEGGINGS);
        register("electronic_boots", ELECTRONIC_BOOTS);

        register("solar_generator_module_tier_1", SOLAR_GENERATOR_MODULE_TIER_1);
        register("solar_generator_module_tier_2", SOLAR_GENERATOR_MODULE_TIER_2);
        register("solar_generator_module_tier_3", SOLAR_GENERATOR_MODULE_TIER_3);
        register("lunar_generator_module_tier_1", LUNAR_GENERATOR_MODULE_TIER_1);
        register("lunar_generator_module_tier_2", LUNAR_GENERATOR_MODULE_TIER_2);
        register("lunar_generator_module_tier_3", LUNAR_GENERATOR_MODULE_TIER_3);
        register("burnable_generator_module", BURNABLE_GENERATOR_MODULE);
        register("solunar_generator_module", SOLUNAR_GENERATOR_MODULE);
        register("attacker_module_tier_1", ATTACKER_MODULE_TIER_1);
        register("attacker_module_tier_2", ATTACKER_MODULE_TIER_2);
        register("attacker_module_tier_3", ATTACKER_MODULE_TIER_3);
        register("mining_module_tier_1", MINING_MODULE_TIER_1);
        register("mining_module_tier_2", MINING_MODULE_TIER_2);
        register("feeding_module", FEEDING_MODULE);
        register("speed_module", SPEED_MODULE);
    }

    public static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, ElectroArmor.id(name), item);
    }

    public static Item.Settings defaults() {
        return new Item.Settings().group(ElectroArmor.ITEM_GROUP);
    }
}
