package io.github.tunacan427.electroarmor.energy;

import nerdhub.cardinal.components.api.component.Component;

public interface EnergyComponent extends Component {
    int getValue();
    void setValue(int value);
    void incrementValue(int amount);
    boolean decrementValue(int amount);
}