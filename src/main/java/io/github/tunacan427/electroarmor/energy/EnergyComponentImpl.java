package io.github.tunacan427.electroarmor.energy;

import net.minecraft.nbt.CompoundTag;

public class EnergyComponentImpl implements EnergyComponent {
    private int value = 0;

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public int setValue(int value) {
        return this.value = value;
    }

    @Override
    public int incrementValue(int amount) {
        return this.value += amount;
    }

    @Override
    public int decrementValue(int amount) {
        return this.value -= amount;
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
