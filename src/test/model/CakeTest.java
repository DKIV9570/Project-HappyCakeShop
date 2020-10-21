package model;

import model.Cake;
import model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the cake class
 */
public class CakeTest {
    private Cake testCake;
    private Material material1 = new Material("a",20,"cake base",1);
    private Material material2 = new Material("b",10,"cream",2);
    private Material material3 = new Material("c",5,"topping",3);

    @BeforeEach
    void runBefore() {
        testCake = new Cake(material1,material2,material3);
    }

    @Test
    void testConstructor() {
        assertEquals(" \" a/b/c cake \" ", testCake.getName());
        assertEquals(0, testCake.getPrice());
        assertEquals(0, testCake.getInventory());
        assertEquals("a", testCake.getCakeBase());
        assertEquals("b", testCake.getCream());
        assertEquals("c", testCake.getTopping());
    }

    @Test
    void testSetPrice() {
        for (int i = 1; i <= 50; i++) {
            testCake.setPrice(i);
            assertEquals(i, testCake.getPrice());
        }
    }

    @Test
    void testAddInventory() {
        testCake.addInventory(50);
        assertEquals(50, testCake.getInventory());
    }

    @Test
    void testConsumeInventory() {
        testCake.addInventory(50);
        testCake.consumeInventory(20);
        assertEquals(30, testCake.getInventory());
        testCake.consumeInventory(40);
        assertEquals(30, testCake.getInventory());
    }
}
