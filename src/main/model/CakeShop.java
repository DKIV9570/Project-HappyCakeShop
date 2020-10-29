package model;

import exceptions.MaterialException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Represents a cake shop.
 */
public class CakeShop {
    private int funds;                                              // The fund of the shop
    private Map<String,Material> baseInventory;
    private Map<String,Material> creamInventory;
    private Map<String,Material> toppingInventory;
    private Map<String,Cake> cakeInventory;                        // The cake inventory of the shop

    /*
     * REQUIRES: initial fund >= 0.
     * EFFECTS:  set the initial Funds to given value.
     *           initialize the material inventory and cake inventory with 0.
     */
    public CakeShop(int initialFunds, Map<String,List<Material>> materials) {
        funds = initialFunds;
        baseInventory = new LinkedHashMap<>();
        creamInventory = new LinkedHashMap<>();
        toppingInventory = new LinkedHashMap<>();
        cakeInventory = new LinkedHashMap();

        for (Material material :materials.get("cake base")) {
            baseInventory.put(material.getName(),material); //stub
        }
        for (Material material :materials.get("cream")) {
            creamInventory.put(material.getName(),material); //stub
        }
        for (Material material :materials.get("topping")) {
            toppingInventory.put(material.getName(),material); //stub
        }
    }

    public int getFunds() {
        return funds;
    }

    public Map<String, Cake> getCakeInventory() {
        return cakeInventory;
    }

    public Map<String, Material> getBaseInventory() {
        return baseInventory;
    }

    public Map<String, Material> getCreamInventory() {
        return creamInventory;
    }

    public Map<String, Material> getToppingInventory() {
        return toppingInventory;
    }

    /*
     * REQUIRES: fund remaining >= price of the material going to buy
     * MODIFIES: this
     * EFFECTS: use the funds to buy materials in the market
     */
    public void buyMaterial(Material material) {
        if (funds >= material.getPrice()) {
            funds -= material.getPrice();
            switch (material.getKind()) {
                case "cake base":
                    baseInventory.get(material.getName()).addInventory(1);
                    break;
                case "cream":
                    creamInventory.get(material.getName()).addInventory(1);
                    break;
                case "topping":
                    toppingInventory.get(material.getName()).addInventory(1);
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: use the chosen raw materials to make a cake
     */
    public int makeCake(Material base,Material cream,Material topping,int price,int number) {

        try {
            baseInventory.get(base.getName()).consumeInventory(number);
        } catch (MaterialException e) {
            return 1;
        }
        try {
            creamInventory.get(cream.getName()).consumeInventory(number);
        } catch (MaterialException e) {
            baseInventory.get(base.getName()).addInventory(number);
            return 2;
        }
        try {
            toppingInventory.get(topping.getName()).consumeInventory(number);
        } catch (MaterialException e) {
            baseInventory.get(base.getName()).addInventory(number);
            creamInventory.get(base.getName()).addInventory(number);
            return 3;
        }
        return nameCake(base,cream,topping,price,number);
    }

    /*
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: name the cake
     */
    public int nameCake(Material base,Material cream,Material topping,int price,int number) {
        String name = " \" " + base.getName() + "/" + cream.getName() + "/" + topping.getName() + " cake \" ";
        if (!cakeInventory.containsKey(name)) {
            Cake newCake = new Cake(base,cream,topping);
            cakeInventory.put(name, newCake);
        }
        cakeInventory.get(name).setPrice(price);
        cakeInventory.get(name).addInventory(number);
        return 0;
    }

    /*
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: sell cake
     */
    public void sellCake(List<Resident> town) {
        for (Resident resident: town) {
            Cake cakeBought = resident.purchase(cakeInventory.values());
            if (cakeBought != null) {
                funds += cakeBought.getPrice();
                cakeBought.consumeInventory(1);
            }
        }
    }



}
