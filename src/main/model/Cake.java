package model;

/*
 * Represents a cake.
 */
public class Cake {

    private Material cakeBase;
    private Material cream;
    private Material topping;
    private int price;
    private int inventory;
    private String name;

    /*
     * EFFECTS: Make a cake use the given materials and set price to 0. Initialize the name
     */
    public Cake(Material cakeBase,Material cream,Material topping) {
        this.cakeBase = cakeBase;
        this.cream = cream;
        this.topping = topping;
        price = 0;
        inventory = 0;
        name = " \" " + cakeBase.getName() + "/" + cream.getName() + "/" + topping.getName() + " cake \"";
    }

    public String getCakeBase() {
        return cakeBase.getName();
    }

    public String getCream() {
        return cream.getName();
    }

    public String getTopping() {
        return topping.getName();
    }

    public int getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    /*
     * REQUIRES: price >= 0.
     * MODIFIES: this
     * EFFECTS:  set the price of this kind of cake.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /*
     * REQUIRES: number >= 0.
     * MODIFIES: this
     * EFFECTS:  add inventory with given number
     */
    public void addInventory(int number) {
        inventory += number;
    }

    /*
     * REQUIRES: number >= 0
     * MODIFIES: this
     * EFFECTS:  consume inventory with given number if inventory >= given number
     */
    public void consumeInventory(int number) {
        if (inventory >= number) {
            inventory -= number;
        }
    }
}
