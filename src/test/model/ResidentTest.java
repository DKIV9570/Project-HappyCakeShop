package model;

import model.Material;
import model.Resident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Resident class
 */
public class ResidentTest {
    private Resident testResident;
    private Material base1 = new Material("base1",10,"cake base",1);
    private Material base2 = new Material("base2",10,"cake base",2);
    private Material base3 = new Material("base3",10,"cake base",3);
    private Material cream1 = new Material("cream1",10,"cream",1);
    private Material cream2 = new Material("cream2",10,"cream",2);
    private Material cream3 = new Material("cream3",10,"cream",3);
    private Material topping1 = new Material("topping1",10,"topping",1);
    private Material topping2 = new Material("topping2",10,"topping",2);
    private Material topping3 = new Material("topping3",10,"topping",1);

    private List<Material> bases = Arrays.asList(base1,base2,base3);
    private List<Material> creams = Arrays.asList(cream1,cream2,cream3);
    private List<Material> toppings = Arrays.asList(topping1,topping2,topping3);

    private Cake cake1 = new Cake(base1,cream1,topping1);
    private Cake cake2 = new Cake(base2,cream2,topping2);
    private Cake cake3 = new Cake(base3,cream3,topping3);

    private Map<String, List<Material>> market = new LinkedHashMap<>();
    private Collection<Cake> cakeInventory = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        market.put("cake base",bases);
        market.put("cream",creams);
        market.put("topping",toppings);
        testResident = new Resident(market);
    }

    @Test
    void testConstructor() {
        assertTrue(testResident.getPurchasePower() <= 60);
        assertTrue(testResident.getPurchasePower() >= 30);
        for (String favor:testResident.getFavors().keySet()) {
            assertTrue(testResident.getFavors().get(favor) <= 1);
            assertTrue(testResident.getFavors().get(favor) >= 0);
        }
    }

    @Test
    void testConstructor2() {
        int purchasePower = testResident.getPurchasePower();
        Map<String,Float> favors = testResident.getFavors();
        Resident resident2 = new Resident(purchasePower,favors);

        assertTrue(testResident.getPurchasePower() <= 60);
        assertTrue(testResident.getPurchasePower() >= 30);
        for (String favor:testResident.getFavors().keySet()) {
            assertTrue(testResident.getFavors().get(favor) <= 1);
            assertTrue(testResident.getFavors().get(favor) >= 0);
        }
    }

    @Test
    void testPurchase1() {
        cake1.setPrice(10);
        cake1.addInventory(0);
        cake2.setPrice(150);
        cake2.addInventory(1);
        cake3.setPrice(150);
        cake3.addInventory(1);
        cakeInventory.add(cake1);
        cakeInventory.add(cake2);
        cakeInventory.add(cake3);
        assertEquals(testResident.purchase(cakeInventory),null);
    }

    @Test
    void testPurchase2() {
        cake1.setPrice(10);
        cake1.addInventory(1);
        cake2.setPrice(10);
        cake2.addInventory(1);
        cake3.setPrice(10);
        cake3.addInventory(1);
        cakeInventory.add(cake1);
        cakeInventory.add(cake2);
        cakeInventory.add(cake3);
        assertNotEquals(testResident.purchase(cakeInventory),null);
    }

    @Test
    void testPurchase3() {
        cake1.setPrice(1);
        cake1.addInventory(1);
        cakeInventory.add(cake1);
        assertEquals(testResident.purchase(cakeInventory),cake1);
    }

    @Test
    void testPurchase4() {
        cake1.setPrice(1);
        cakeInventory.add(cake1);
        assertEquals(testResident.purchase(cakeInventory),null);
    }
}
