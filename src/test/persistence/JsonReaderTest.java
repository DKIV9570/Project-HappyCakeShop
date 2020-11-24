package persistence;

import model.Material;
import model.Resident;
import org.junit.jupiter.api.Test;
import ui.CSG;
import ui.GameMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonReader class, referenced some design idea of CPSC210.JsonSerializationDemo project
 */
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CSG csg = reader.read(new CSG(20));
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNewCSG() {
        try {
            CSG csg = new CSG(20);

            JsonReader reader = new JsonReader("./data/testWriterNewCSG.json");
            csg = reader.read(new CSG(10));
            assertEquals(20, csg.getTotalRound());
            assertEquals(1, csg.getCurrentRound());
            assertEquals(3, csg.getShop().getBaseInventory().size());
            assertEquals(3, csg.getShop().getCreamInventory().size());
            assertEquals(3, csg.getShop().getToppingInventory().size());
            assertEquals(1000, csg.getShop().getFunds());
            assertEquals(0, csg.getShop().getCakeInventory().size());
            assertEquals(500, csg.getTown().getResidents().size());
            assertEquals(3, csg.getTown().getMarket().size());

            Material testMaterial = csg.getShop().getBaseInventory().get("Soft base");
            checkMaterial("Soft base",testMaterial.getPrice(),
                    "cake base",1,0,testMaterial);
            checkMaterialInventory(3,csg.getShop().getBaseInventory());
            checkMaterialInventory(3,csg.getShop().getCreamInventory());
            checkMaterialInventory(3,csg.getShop().getToppingInventory());

            Resident testResident = csg.getTown().getResidents().get(0);
            checkResident(9,testResident);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testReaderNewGameMenu() {
        try {
            GameMenu gameMenu;

            JsonReader reader = new JsonReader("./data/testWriterNewGameMenu.json");
            gameMenu = reader.read(new GameMenu(10));
            assertEquals(3, gameMenu.getShop().getBaseInventory().size());
            assertEquals(3, gameMenu.getShop().getCreamInventory().size());
            assertEquals(3, gameMenu.getShop().getToppingInventory().size());
            assertEquals(1000, gameMenu.getShop().getFunds());
            assertEquals(0, gameMenu.getShop().getCakeInventory().size());
            assertEquals(500, gameMenu.getTown().getResidents().size());
            assertEquals(3, gameMenu.getTown().getMarket().size());

            Material testMaterial = gameMenu.getShop().getBaseInventory().get("Soft base");
            checkMaterial("Soft base",testMaterial.getPrice(),
                    "cake base",1,0,testMaterial);
            checkMaterialInventory(3,gameMenu.getShop().getBaseInventory());
            checkMaterialInventory(3,gameMenu.getShop().getCreamInventory());
            checkMaterialInventory(3,gameMenu.getShop().getToppingInventory());

            Resident testResident = gameMenu.getTown().getResidents().get(0);
            checkResident(9,testResident);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}
