package persistence;

import model.Cake;
import model.Material;
import model.Resident;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    protected void checkMaterialInventory(int size, Map<String, Material> materialInventory) {
        assertEquals(size,materialInventory.size());
    }

    protected void checkCakeInventory(int size, Map<String, Cake> cakeInventory) {
        assertEquals(size,cakeInventory.size());
    }

    protected void checkResident(int size, Resident resident) {
        assertEquals(size,resident.getFavors().size());
        assertTrue(resident.getPurchasePower() < 1000000);
    }






}
