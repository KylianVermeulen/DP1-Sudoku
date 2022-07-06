package dev.kylian;

import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        PrintWriter writer = new PrintWriter(System.out);

        Item item = new Item("kylian", "4");
        writer.println(item);
        writer.flush();


        item = new Item("kylian", "5");
        writer.println(item);
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
