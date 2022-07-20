package dev.kylian.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BoxComposite implements Component {
    private List<Component> components;

    public BoxComposite(List<Component> components) {
        this.components = components;
    }

    @Override
    public void place(Coordinate coordinate, int value, boolean locked) {
        this.components.forEach(c -> c.place(coordinate, value, locked));
    }

    @Override
    public String draw() {
        AtomicReference<String> result = new AtomicReference<>("");
        this.components.forEach(c -> result.set(result + c.draw()));
        return result.get();
    }
}
