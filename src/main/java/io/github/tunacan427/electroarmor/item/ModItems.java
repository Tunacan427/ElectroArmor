package io.github.tunacan427.electroarmor.item;

import io.github.tunacan427.electroarmor.ElectroArmor;
import io.github.tunacan427.electroarmor.item.armor.CustomArmorMaterial;
import io.github.tunacan427.electroarmor.item.upgrade.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item IRON_PLATE = new Item(defaults());
    public static final Item CIRCUIT = new Item(defaults());
    public static final Item HAMMER = new HammerItem();
    public static final Item BASE_UPGRADE = new Item(defaults());

    public static final Item ELECTRONIC_HELMET = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.HEAD, defaults());
    public static final Item ELECTRONIC_CHESTPLATE = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.CHEST, defaults());
    public static final Item ELECTRONIC_LEGGINGS = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.LEGS, defaults());
    public static final Item ELECTRONIC_BOOTS = new ArmorItem(CustomArmorMaterial.ELECTRONIC, EquipmentSlot.FEET, defaults());

    public static final Item SOLAR_GENERATOR_UPGRADE_TIER_1 = new SolarGeneratorUpgrade(1);
    public static final Item SOLAR_GENERATOR_UPGRADE_TIER_2 = new SolarGeneratorUpgrade(2);
    public static final Item SOLAR_GENERATOR_UPGRADE_TIER_3 = new SolarGeneratorUpgrade(3);
    public static final Item LUNAR_GENERATOR_UPGRADE_TIER_1 = new LunarGeneratorUpgrade(1);
    public static final Item LUNAR_GENERATOR_UPGRADE_TIER_2 = new LunarGeneratorUpgrade(2);
    public static final Item LUNAR_GENERATOR_UPGRADE_TIER_3 = new LunarGeneratorUpgrade(3);
    public static final Item BURNABLE_GENERATOR_UPGRADE = new BurnableGeneratorUpgrade(1);
    public static final Item SOLUNAR_GENERATOR_UPGRADE = new SolunarGeneratorUpgrade(1);
    public static final Item ATTACKER_UPGRADE_TIER_1 = new AttackerUpgrade(1);
    public static final Item ATTACKER_UPGRADE_TIER_2 = new AttackerUpgrade(2);
    public static final Item ATTACKER_UPGRADE_TIER_3 = new AttackerUpgrade(3);
    public static final Item MINING_UPGRADE_TIER_1 = new MiningUpgrade(1);
    public static final Item MINING_UPGRADE_TIER_2 = new MiningUpgrade(2);
    public static final Item FEEDING_UPGRADE = new FeedingUpgrade(1);
    public static final Item SPEED_UPGRADE = new SpeedUpgrade(1);

    public static void registerItems() {
        register("iron_plate", IRON_PLATE);
        register("circuit", CIRCUIT);
        register("hammer", HAMMER);
        register("base_upgrade", BASE_UPGRADE);

        register("electronic_helmet", ELECTRONIC_HELMET);
        register("electronic_chestplate", ELECTRONIC_CHESTPLATE);
        register("electronic_leggings", ELECTRONIC_LEGGINGS);
        register("electronic_boots", ELECTRONIC_BOOTS);

        register("solar_generator_upgrade_tier_1", SOLAR_GENERATOR_UPGRADE_TIER_1);
        register("solar_generator_upgrade_tier_2", SOLAR_GENERATOR_UPGRADE_TIER_2);
        register("solar_generator_upgrade_tier_3", SOLAR_GENERATOR_UPGRADE_TIER_3);
        register("lunar_generator_upgrade_tier_1", LUNAR_GENERATOR_UPGRADE_TIER_1);
        register("lunar_generator_upgrade_tier_2", LUNAR_GENERATOR_UPGRADE_TIER_2);
        register("lunar_generator_upgrade_tier_3", LUNAR_GENERATOR_UPGRADE_TIER_3);
        register("burnable_generator_upgrade", BURNABLE_GENERATOR_UPGRADE);
        register("solunar_generator_upgrade", SOLUNAR_GENERATOR_UPGRADE);
        register("attacker_upgrade_tier_1", ATTACKER_UPGRADE_TIER_1);
        register("attacker_upgrade_tier_2", ATTACKER_UPGRADE_TIER_2);
        register("attacker_upgrade_tier_3", ATTACKER_UPGRADE_TIER_3);
        register("mining_upgrade_tier_1", MINING_UPGRADE_TIER_1);
        register("mining_upgrade_tier_2", MINING_UPGRADE_TIER_2);
        register("feeding_upgrade", FEEDING_UPGRADE);
        register("speed_upgrade", SPEED_UPGRADE);
    }

    public static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, ElectroArmor.id(name), item);
    }

    public static Item.Settings defaults() {
        return new Item.Settings().group(ElectroArmor.ITEM_GROUP);
    }
}
