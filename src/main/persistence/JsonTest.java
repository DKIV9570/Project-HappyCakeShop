package persistence;

import model.Cake;
import model.Material;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCake(String name, Material cakeBase,
                             Material cream,Material topping,int price, int inventory, Cake cake) {
        assertEquals(name, cake.getName());
        assertEquals(cakeBase, cake.getCakeBase());
        assertEquals(cream, cake.getCream());
        assertEquals(topping, cake.getTopping());
        assertEquals(price, cake.getPrice());
        assertEquals(inventory, cake.getInventory());
    }

    protected void checkMaterial(String name, int price,
                                 String kind,int serialNumber,int inventory, Material material) {
        assertEquals(name, material.getName());
        assertEquals(kind, material.getKind());
        assertEquals(serialNumber, material.getSerialNumber());
        assertEquals(price, material.getPrice());
        assertEquals(inventory, material.getInventory());
    }
}
