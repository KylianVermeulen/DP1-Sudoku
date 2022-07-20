package dev.kylian;

import dev.kylian.domain.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        PrintWriter writer = new PrintWriter(System.out);

        Item item = new Item("kylian", "4");
        writer.println(item);
        writer.flush();

        item = new Item("kylian", "5");
        writer.println(item);
        writer.flush();

        Board board = new Board();
        List<Component> components = new ArrayList<>();
        components.add(new BoxComposite(List.of(new Cell(new Coordinate(0, 0)), new Cell(new Coordinate(1,0)))));
        components.add(new BoxComposite(List.of(new Cell(new Coordinate(0, 1)), new Cell(new Coordinate(1,1)))));
        components.add(new BoxComposite(List.of(new Cell(new Coordinate(0, 2)), new Cell(new Coordinate(1,2)))));
        board.setComponents(components);
        board.getComponents().forEach(component -> {
            writer.println(component.draw());
        });
        writer.flush();
    }

    static class Item {
        Item(String name, String price) {
            this.name = name;
            this.price = price;
        }

        String name, price;

        @Override
        public String toString() {
            return "Name: " + name + "\n"
                    + "Price: " + price;
        }
    }
}
