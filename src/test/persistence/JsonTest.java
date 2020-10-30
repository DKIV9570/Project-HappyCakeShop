package persistence;

import model.Cake;
import model.Material;
import model.Resident;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {

    /*
    EFFECTS: check the Cake with given properties
    */
    protected void checkCake(String name, Material cakeBase,
                             Material cream,Material topping,int price, int inventory, Cake cake) {
        assertEquals(name, cake.getName());
        assertEquals(cakeBase, cake.getCakeBase());
        assertEquals(cream, cake.getCream());
        assertEquals(topping, cake.getTopping());
        assertEquals(price, cake.getPrice());
        assertEquals(inventory, cake.getInventory());
    }

    /*
    EFFECTS: check the Material with given properties
    */
    protected void checkMaterial(String name, int price,
                                 String kind,int serialNumber,int inventory, Material material) {
        assertEquals(name, material.getName());
        assertEquals(kind, material.getKind());
        assertEquals(serialNumber, material.getSerialNumber());
        assertEquals(price, material.getPrice());
        assertEquals(inventory, material.getInventory());
    }

    /*
    EFFECTS: check the Material Inventory with given properties
    */
    protected void checkMaterialInventory(int size, Map<String, Material> materialInventory) {
        assertEquals(size,materialInventory.size());
    }

    /*
    EFFECTS: check the Cake Inventory with given properties
    */
    protected void checkCakeInventory(int size, Map<String, Cake> cakeInventory) {
        assertEquals(size,cakeInventory.size());
    }

    /*
    EFFECTS: check the resident with given properties
    */
    protected void checkResident(int size, Resident resident) {
        assertEquals(size,resident.getFavors().size());
        assertTrue(resident.getPurchasePower() < 1000000);
    }






}
