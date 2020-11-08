package model;

import exceptions.MaterialException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * Represents a cake shop.
 */
public class CakeShop implements Writable {
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

    /*
     * EFFECTS:  read the values and use them to initialize
     */
    public CakeShop(int funds,Map<String,Material> baseInventory,Map<String,Material> creamInventory,
                    Map<String,Material> toppingInventory,Map<String,Cake> cakeInventory) {
        this.funds = funds;
        this.baseInventory = baseInventory;
        this.creamInventory = creamInventory;
        this.toppingInventory = toppingInventory;
        this.cakeInventory = cakeInventory;
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
    public void buyMaterial(Material material, int number) {
        if (funds >= material.getPrice() * number) {
            funds -= material.getPrice() * number;
            switch (material.getKind()) {
                case "cake base":
                    baseInventory.get(material.getName()).addInventory(number);
                    break;
                case "cream":
                    creamInventory.get(material.getName()).addInventory(number);
                    break;
                case "topping":
                    toppingInventory.get(material.getName()).addInventory(number);
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: use the chosen raw materials to make a cake
     */
    public int makeCake(String base, String cream, String topping, int number) {

        try {
            baseInventory.get(base).consumeInventory(number);
        } catch (MaterialException e) {
            return 1;
        }
        try {
            creamInventory.get(cream).consumeInventory(number);
        } catch (MaterialException e) {
            baseInventory.get(base).addInventory(number);
            return 2;
        }
        try {
            toppingInventory.get(topping).consumeInventory(number);
        } catch (MaterialException e) {
            baseInventory.get(base).addInventory(number);
            creamInventory.get(base).addInventory(number);
            return 3;
        }
        return nameCake(base,cream,topping, number);
    }

    /*
     * MODIFIES: this
     * EFFECTS: name the cake
     */
    public int nameCake(String base, String cream, String topping, int number) {
        String name = " \" " + base + "/" + cream + "/" + topping + " cake \" ";
        if (!cakeInventory.containsKey(name)) {
            Cake newCake = new Cake(baseInventory.get(base),creamInventory.get(cream),toppingInventory.get(topping));
            cakeInventory.put(name, newCake);
        }
        cakeInventory.get(name).addInventory(number);
        return 0;
    }

    /*
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
        Iterator<String> iterator = cakeInventory.keySet().iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (cakeInventory.get(s).getInventory() == 0) {
                iterator.remove();
            }
        }

    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonShop = new JSONObject();
        jsonShop.put("funds",funds);
        jsonShop.put("baseInventory", materialInventoryToJson(baseInventory));
        jsonShop.put("creamInventory", materialInventoryToJson(creamInventory));
        jsonShop.put("toppingInventory", materialInventoryToJson(toppingInventory));
        jsonShop.put("cakeInventory",cakeInventoryToJson(cakeInventory));
        return jsonShop;
    }

    /*
     * EFFECTS: returns a kind of material inventory of the shop as an JSONArray.
     */
    public JSONArray materialInventoryToJson(Map<String, Material> inventory) {
        JSONArray jsonArray = new JSONArray();
        for (String key: inventory.keySet()) {
            jsonArray.put(inventory.get(key).toJson());
        }
        return jsonArray;
    }

    /*
     * EFFECTS: returns a cake inventory of the shop as an JSONArray.
     */
    public JSONArray cakeInventoryToJson(Map<String, Cake> inventory) {
        JSONArray jsonArray = new JSONArray();
        for (String key: inventory.keySet()) {
            jsonArray.put(inventory.get(key).toJson());
        }
        return jsonArray;
    }

}
