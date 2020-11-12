package model;

import model.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the material class
 */
public class MaterialTest {
    private Material testMaterial;
    private Material testMaterial2;
    private Material testMaterial3;
    private Material testMaterial4;

    @BeforeEach
    void runBefore() {
        testMaterial = new Material("test",10,"cake base",1);
        testMaterial2 = new Material("test",10,"cake base",1);
        testMaterial3 = new Material("test3",10,"cake base",1);
        testMaterial4 = new Material("test",10,"none",1);
    }

    @Test
    void testConstructor() {
        assertEquals("test", testMaterial.getName());
        assertEquals(10, testMaterial.getPrice());
        assertEquals("cake base", testMaterial.getKind());
        assertEquals(1,testMaterial.getSerialNumber());
    }

    @Test
    void testEquals() {
        assertTrue(testMaterial.equals(testMaterial2));
        assertFalse(testMaterial.equals(testMaterial3));
        assertFalse(testMaterial.equals(testMaterial4));
    }

    @Test
    void testHashCode() {
        assertEquals(testMaterial.hashCode(),testMaterial2.hashCode());
        assertNotEquals(testMaterial.hashCode(),testMaterial3.hashCode());
        assertNotEquals(testMaterial.hashCode(),testMaterial4.hashCode());

    }
}