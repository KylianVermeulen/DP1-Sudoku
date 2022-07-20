package dev.kylian.domain;

import java.util.List;

public class Board implements Component {
    private List<Component> regions;

    @Override
    public boolean validate() {
        return regions.stream().allMatch(Component::validate);
    }

    public void setRegions(List<Component> regions) {
        this.regions = regions;
    }
}
