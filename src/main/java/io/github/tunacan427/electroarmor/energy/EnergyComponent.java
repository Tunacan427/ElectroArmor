package io.github.tunacan427.electroarmor.energy;

import nerdhub.cardinal.components.api.component.Component;

public interface EnergyComponent extends Component {
    int getValue();
    int setValue(int value);
    int incrementValue(int amount);
    int decrementValue(int amount);
}