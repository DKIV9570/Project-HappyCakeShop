package model;

import java.util.*;

/*
 * Represents the town
 */
public class Town {
    private List<Resident> residents = new LinkedList<>();                    //residents of the town
    private Map<String,List<Material>> market = new LinkedHashMap<>();        //The market in the town
    private List<String> bases = Arrays.asList("Soft base","Hard base","Medium base");
    private List<String> creams = Arrays.asList("Pure cream","Chocolate cream","Blueberry cream");
    private List<String> toppings = Arrays.asList("Apple topping","Banana topping","Nuts topping");

    /*
     * EFFECTS: initialize the town
     */
    public Town() {
        //initialize the market
        initializeMarket(bases,"cake base");
        initializeMarket(creams,"cream");
        initializeMarket(toppings,"topping");

        //initialize the residents of the town
        //The population of the town
        int population = 500;
        for (int i = 1; i <= population; i++) {
            Resident resident = new Resident(market);
            residents.add(resident);
        }
    }

    /*
     * EFFECTS: add a kind of material to the market
     */
    protected void initializeMarket(List<String> kind, String name) {
        List<Material> listOfKind = new ArrayList<>();
        for (int i = 0; i < kind.size(); i++) {
            int price = (int) ((Math.random() * 19) + 1);
            Material material = new Material(kind.get(i),price,name,i + 1);
            listOfKind.add(material);
        }
        market.put(name,listOfKind);
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public Map<String, List<Material>> getMarket() {
        return market;
    }
}
