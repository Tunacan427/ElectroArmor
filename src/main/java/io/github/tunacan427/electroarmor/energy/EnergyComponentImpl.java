package io.github.tunacan427.electroarmor.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;

public class EnergyComponentImpl implements EnergyComponent {
    public static final int MAX_ENERGY = 10000;
    private int value = 0;

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void setValue(int value) {
        this.value = MathHelper.clamp(value, 0, MAX_ENERGY);
    }

    @Override
    public void incrementValue(int amount) {
        this.value = MathHelper.clamp(this.value + amount, 0, MAX_ENERGY);
    }

    @Override
    public boolean decrementValue(int amount) {
        this.value = MathHelper.clamp(this.value - amount, 0, MAX_ENERGY);
        if (this.value == 0) {
            return false;
        }
        return true;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.value = tag.getInt("energyValue");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("energyValue", this.value);
        return tag;
    }
}
