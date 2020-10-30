package model;

import exceptions.MaterialException;
import model.CakeShop;
import model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the cake shop class
 */
public class CakeShopTest {
    private CakeShop testShop;
    private Material base1 = new Material("base1",10,"cake base",1);
    private Material base2 = new Material("base2",10,"cake base",2);
    private Material base3 = new Material("base3",1001,"cake base",3);
    private Material cream1 = new Material("cream1",10,"cream",1);
    private Material cream2 = new Material("cream2",10,"cream",2);
    private Material topping1 = new Material("topping1",10,"topping",1);
    private Material topping2 = new Material("topping2",10,"topping",2);

    private Material notAMaterial = new Material("none",10,"none",1);

    private List<Material> bases = Arrays.asList(base1,base2,base3);
    private List<Material> creams = Arrays.asList(cream1,cream2);
    private List<Material> toppings = Arrays.asList(topping1,topping2);

    private Cake cake1 = new Cake(base1,cream1,topping1);
    private Cake cake2 = new Cake(base2,cream2,topping2);

    private Map<String, List<Material>> market = new LinkedHashMap<>();

    @BeforeEach
    void runBefore() {
        market.put("cake base",bases);
        market.put("cream",creams);
        market.put("topping",toppings);
        testShop = new CakeShop(1000,market);
    }

    @Test
    void testConstructor() {
        for (String name: testShop.getBaseInventory().keySet()) {
            assertEquals(testShop.getBaseInventory().get(name).getInventory(),0);
        }
        for (String name: testShop.getCreamInventory().keySet()) {
            assertEquals(testShop.getCreamInventory().get(name).getInventory(),0);
        }
        for (String name: testShop.getToppingInventory().keySet()) {
            assertEquals(testShop.getToppingInventory().get(name).getInventory(),0);
        }
        assertTrue(testShop.getCakeInventory().isEmpty());
        assertEquals(testShop.getFunds(),1000);
    }

    @Test
    void testConstructor2() {
        int funds = testShop.getFunds();
        Map<String,Material> baseInventory = testShop.getBaseInventory();
        Map<String,Material> creamInventory = testShop.getCreamInventory();
        Map<String,Material> toppingInventory = testShop.getToppingInventory();
        Map<String,Cake> cakeInventory = testShop.getCakeInventory();
        CakeShop anotherShop = new CakeShop(funds,baseInventory,creamInventory,toppingInventory,cakeInventory);
        assertTrue(testShop.getCakeInventory().isEmpty());
        assertEquals(testShop.getFunds(),1000);
    }

    @Test
    void testBuyMaterial1() {
        testShop.buyMaterial(base3);
        testShop.buyMaterial(notAMaterial);
        assertEquals(testShop.getFunds(),990);
        assertEquals(testShop.getBaseInventory().get("base3").getInventory(),0);
    }

    @Test
    void testBuyMaterial2() {
        testShop.buyMaterial(notAMaterial);
        assertEquals(testShop.getFunds(),990);
    }

    @Test
    void testBuyMaterial3() {
        testShop.buyMaterial(base2);
        assertEquals(testShop.getFunds(),990);
        testShop.buyMaterial(cream1);
        assertEquals(testShop.getFunds(),980);
        testShop.buyMaterial(topping1);
        assertEquals(testShop.getFunds(),970);
        testShop.buyMaterial(base2);
        assertEquals(testShop.getFunds(),960);
        assertEquals(testShop.getBaseInventory().get("base2").getInventory(),2);
        assertEquals(testShop.getCreamInventory().get("cream1").getInventory(),1);
        assertEquals(testShop.getToppingInventory().get("topping1").getInventory(),1);
    }

    @Test
    void testMakeCake() {
        for (int i = 1; i <= 10; i++) {
            testShop.buyMaterial(base1);
            testShop.buyMaterial(cream1);
            testShop.buyMaterial(topping1);

            testShop.buyMaterial(base2);
            testShop.buyMaterial(cream2);
            testShop.buyMaterial(topping2);
        }

        testShop.makeCake(base1.getName(),cream1.getName(),topping1.getName(),10,1);



        testShop.makeCake(base1.getName(),cream1.getName(),topping1.getName(),10,1);

        String name1 = " \" base1/cream1/topping1 cake \" ";
        assertEquals(testShop.getCakeInventory().size(),1);

        testShop.makeCake(base2.getName(),cream2.getName(),topping2.getName(),10,2);

        assertEquals(testShop.getCakeInventory().size(),2);

    }

    @Test
    void testSellCake1() {
        Resident resident1 = new Resident(market);
        Resident resident2 = new Resident(market);
        List<Resident> town = Arrays.asList(resident1);
        testShop.buyMaterial(base1);
        testShop.buyMaterial(cream1);
        testShop.buyMaterial(topping1);

        testShop.makeCake(base1.getName(), cream1.getName(), topping1.getName(), 10000, 1);
        testShop.sellCake(town);
        assertEquals(testShop.getCakeInventory().size(),1);
    }

    @Test
    void testSellCake2() {
        Resident resident1 = new Resident(market);
        List<Resident> town = Arrays.asList(resident1);
        testShop.buyMaterial(base1);
        testShop.buyMaterial(cream1);
        testShop.buyMaterial(topping1);
        testShop.makeCake(base1.getName(),cream1.getName(),topping1.getName(),1,1);
        String nameOfCake = " \" " + base1.getName() + "/" + cream1.getName() + "/" + topping1.getName() + " cake \" ";
        testShop.sellCake(town);
        assertEquals(testShop.getCakeInventory().get(nameOfCake).getInventory(),0);
    }
}
