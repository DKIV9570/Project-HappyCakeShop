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

    @Test
    void testSetInventory() {
        testCake.setInventory(10);
        assertEquals(testCake.getInventory(),10);
        testCake.setInventory(20);
        assertEquals(testCake.getInventory(),20);
    }

    @Test
    void testEquals() {
        Material material4 = new Material("a",20,"cake base",1);
        Material material5 = new Material("b",10,"cream",2);
        Material material6 = new Material("c",5,"topping",3);
        Cake anotherCake = new Cake(material4,material5,material6);
        assertTrue(testCake.equals(anotherCake));
        anotherCake.setPrice(100);
        assertTrue(testCake.equals(anotherCake));

        Material material7 = new Material("d",20,"cake base",1);
        Material material8 = new Material("e",10,"cream",2);
        Material material9 = new Material("f",5,"topping",3);
        Cake differentCake = new Cake(material7,material8,material9);
        assertFalse(testCake.equals(differentCake));
    }

    @Test
    void testHashCode() {
        Material material4 = new Material("a",20,"cake base",1);
        Material material5 = new Material("b",10,"cream",2);
        Material material6 = new Material("c",5,"topping",3);
        Cake anotherCake = new Cake(material4,material5,material6);
        assertEquals(testCake.hashCode(),anotherCake.hashCode());
        anotherCake.setPrice(100);
        assertEquals(testCake.hashCode(),anotherCake.hashCode());

        Material material7 = new Material("d",20,"cake base",1);
        Material material8 = new Material("e",10,"cream",2);
        Material material9 = new Material("f",5,"topping",3);
        Cake differentCake = new Cake(material7,material8,material9);
        assertNotEquals(testCake.hashCode(),differentCake.hashCode());
    }
}
