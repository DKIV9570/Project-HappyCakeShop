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

    @BeforeEach
    void runBefore() {
        testMaterial = new Material("test",10,"cake base",1);
    }

    @Test
    void testConstructor() {
        assertEquals("test", testMaterial.getName());
        assertEquals(10, testMaterial.getPrice());
        assertEquals("cake base", testMaterial.getKind());
        assertEquals(1,testMaterial.getSerialNumber());
    }
}