package model;

import java.lang.reflect.Array;
import java.nio.channels.CancelledKeyException;
import java.util.*;

/*
 * Represents a resident in the town.
 * Note: resident is a Underlying data which not intended to be accessed by players.
 */
public class Resident {

    private int purchasePower;
    private Map<String,Float> favors = new LinkedHashMap<>();

    /*
     * EFFECTS: randomly initialize the purchase power
     *          randomly initialize the favor for each of the materials
     */
    public Resident(Map<String,List<Material>> market) {
        int sum = 0;

        for (String kind : market.keySet()) {
            for (Material material : market.get(kind)) {
                float favor = (float) Math.random();
                favors.put(material.getName(), favor);
                sum += material.getPrice();
            }
        }
        purchasePower = (int) (Math.random() * (sum / market.size()) + (sum / market.size()));
    }

    /*
     *  only for test use
     */
    public int getPurchasePower() {
        return purchasePower;
    }

    /*
     *  only for test use
     */
    public Map<String, Float> getFavors() {
        return favors;
    }

    /*
     * EFFECTS: go through all kind of the cakes provided by the cake shop and purchase up to 1 cake.
     *          Or not purchase if all the cake's price is over expect.
     */
    public Cake purchase(Collection<Cake> cakeInventory) {
        float favoredMost = 0;
        Cake favoriteCake = null;
        for (Cake cake: cakeInventory) {
            float favorOfCake = favors.get(cake.getCakeBase()) + favors.get(cake.getCream())
                    + favors.get(cake.getTopping());

            boolean likesMore = favorOfCake > favoredMost;
            boolean haveInventory = cake.getInventory() >= 1;
            boolean willingToBuy = cake.getPrice() <= favorOfCake * purchasePower;

            if (likesMore && haveInventory && willingToBuy) {
                favoriteCake = cake;
                favoredMost = favorOfCake;
            }
        }
        return favoriteCake;
    }
}
