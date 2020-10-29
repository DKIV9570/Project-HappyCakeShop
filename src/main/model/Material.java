package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Represents a kind of material
 */
public class Material {
    private String name;        //name of the material
    private int price;          //price of the material
    private String kind;        //kind of the material, which is one of "cake base" "cream" "topping"
    private int serialNumber;   //serial number of a material, unique, for quick inference
    private int inventory;      //the inventory of material in the shop

    /*
     * EFFECTS: initialize the material with the given factors
     */
    public Material(String name, int price, String kind, int serialNumber) {
        this.name = name;
        this.price = price;
        this.kind = kind;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public int getPrice() {
        return price;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getInventory() {
        return inventory;
    }

    /*
     * EFFECTS: add the inventory by 1
     */
    public void addInventory() {
        inventory += 1;
    }

    /*
     * EFFECTS: consume the inventory by the number given
     */
    public void consumeInventory(int number) {
        inventory -= number;
    }
}
