package model;

import exceptions.MaterialException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*
 * Represents a kind of material
 */
public class Material implements Writable {
    private String name;        //name of the material
    private int price;          //price of the material
    private String kind;        //kind of the material, which is one of "cake base" "cream" "topping"
    private int serialNumber;   //serial number of a material, unique, for quick inference
    private int inventory = 0;      //the inventory of material in the shop

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
     * EFFECTS: add the inventory by number
     */
    public void addInventory(int number) {
        inventory += number;
    }

    /*
     * EFFECTS: consume the inventory by the number given, if not enough, throw MaterialException
     */
    public void consumeInventory(int number) throws MaterialException {
        if (inventory >= number) {
            inventory -= number;
        } else {
            throw new MaterialException();
        }
    }

    /*
     * EFFECTS: set the inventory by the number given
     *          only for testing
     */
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonMaterial = new JSONObject();
        jsonMaterial.put("name",name);
        jsonMaterial.put("price",price);
        jsonMaterial.put("kind",kind);
        jsonMaterial.put("serialNumber",serialNumber);
        jsonMaterial.put("inventory",inventory);
        return jsonMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Material material = (Material) o;
        return Objects.equals(name, material.name) && Objects.equals(kind, material.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, kind);
    }
}
