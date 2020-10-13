package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Represents a cake shop.
 */
public class CakeShop {
    private int funds;                                              // The fund of the shop
    private Map<Material,Integer> baseInventory;
    private Map<Material,Integer> creamInventory;
    private Map<Material,Integer> toppingInventory;
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
            baseInventory.put(material,0); //stub
        }
        for (Material material :materials.get("cream")) {
            creamInventory.put(material,0);
        }
        for (Material material :materials.get("topping")) {
            toppingInventory.put(material,0);
        }
    }

    public int getFunds() {
        return funds;
    }

    public Map<String, Cake> getCakeInventory() {
        return cakeInventory;
    }

    public Map<Material, Integer> getBaseInventory() {
        return baseInventory;
    }

    public Map<Material, Integer> getCreamInventory() {
        return creamInventory;
    }

    public Map<Material, Integer> getToppingInventory() {
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
                case "cake base": baseInventory.put(material,baseInventory.get(material) + 1);
                    break;
                case "cream": creamInventory.put(material,creamInventory.get(material) + 1);
                    break;
                case "topping": toppingInventory.put(material,toppingInventory.get(material) + 1);
                    break;
            }
        }
    }

    /*
     * REQUIRES:
     * MODIFIES: this
     * EFFECTS: use the chosen raw materials to make a cake
     */
    public void makeCake(Material base,Material cream,Material topping,int price,int number) {
        baseInventory.put(base,baseInventory.get(base) - number);
        creamInventory.put(cream,creamInventory.get(cream) - number);
        toppingInventory.put(topping,toppingInventory.get(topping) - number);

        String name = " \" " + base.getName() + "/" + cream.getName() + "/" + topping.getName() + " cake \" ";
        if (!cakeInventory.containsKey(name)) {
            Cake newCake = new Cake(base,cream,topping);
            cakeInventory.put(name, newCake);
        }
        cakeInventory.get(name).setPrice(price);
        cakeInventory.get(name).addInventory(number);
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
