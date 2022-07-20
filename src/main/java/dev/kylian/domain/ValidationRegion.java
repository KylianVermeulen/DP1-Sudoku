package dev.kylian.domain;

import java.util.List;

public class ValidationRegion implements Component {
    private List<Component> components;

    @Override
    public boolean validate() {
        return components.stream().allMatch(Component::validate);
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
