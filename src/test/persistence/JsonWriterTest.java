package persistence;

import org.junit.jupiter.api.Test;
import ui.CSG;
import ui.GameMenu;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonWriter class, referenced some design idea of CPSC210.JsonSerializationDemo project
 */
public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CSG csg = new CSG(20);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterNewCSG() {
        try {
            CSG csg = new CSG(20);
            JsonWriter writer = new JsonWriter("./data/testWriterNewCSG.json");
            writer.open();
            writer.write(csg);
            writer.close();

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
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEditedCSG() {
        try {
            CSG csg = new CSG(20);
            csg.getShop().getBaseInventory().get("Soft base").setInventory(5);
            csg.getShop().getCreamInventory().get("Pure cream").setInventory(5);
            csg.getShop().getToppingInventory().get("Apple topping").setInventory(5);
            csg.getShop().makeCake("Soft base","Pure cream","Apple topping", 3);

            JsonWriter writer = new JsonWriter("./data/testWriterEditedCSG.json");
            writer.open();
            writer.write(csg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEditedCSG.json");
            csg = reader.read(new CSG(10));
            assertEquals(20, csg.getTotalRound());
            assertEquals(1, csg.getCurrentRound());
            assertEquals(3, csg.getShop().getBaseInventory().size());
            assertEquals(3, csg.getShop().getCreamInventory().size());
            assertEquals(3, csg.getShop().getToppingInventory().size());
            assertEquals(1000, csg.getShop().getFunds());
            assertEquals(500, csg.getTown().getResidents().size());
            assertEquals(3, csg.getTown().getMarket().size());
            assertEquals(1,csg.getShop().getCakeInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNewGameMenu() {
        try {
            GameMenu gameMenu = new GameMenu(10);
            JsonWriter writer = new JsonWriter("./data/testWriterNewGameMenu.json");
            writer.open();
            writer.write(gameMenu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewGameMenu.json");
            gameMenu = reader.read(new GameMenu(20));
            assertEquals(3, gameMenu.getShop().getBaseInventory().size());
            assertEquals(3, gameMenu.getShop().getCreamInventory().size());
            assertEquals(3, gameMenu.getShop().getToppingInventory().size());
            assertEquals(1000, gameMenu.getShop().getFunds());
            assertEquals(0, gameMenu.getShop().getCakeInventory().size());
            assertEquals(500, gameMenu.getTown().getResidents().size());
            assertEquals(3, gameMenu.getTown().getMarket().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterEditedGameMenu() {
        try {
            GameMenu gameMenu = new GameMenu(20);
            gameMenu.getShop().getBaseInventory().get("Soft base").setInventory(5);
            gameMenu.getShop().getCreamInventory().get("Pure cream").setInventory(5);
            gameMenu.getShop().getToppingInventory().get("Apple topping").setInventory(5);
            gameMenu.getShop().makeCake("Soft base","Pure cream","Apple topping", 3);

            JsonWriter writer = new JsonWriter("./data/testWriterEditedGameMenu.json");
            writer.open();
            writer.write(gameMenu);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEditedGameMenu.json");
            gameMenu = reader.read(new GameMenu(10));
            assertEquals(20, gameMenu.getRoundRemain());
            assertEquals(3, gameMenu.getShop().getBaseInventory().size());
            assertEquals(3, gameMenu.getShop().getCreamInventory().size());
            assertEquals(3, gameMenu.getShop().getToppingInventory().size());
            assertEquals(1000, gameMenu.getShop().getFunds());
            assertEquals(500, gameMenu.getTown().getResidents().size());
            assertEquals(3, gameMenu.getTown().getMarket().size());
            assertEquals(1,gameMenu.getShop().getCakeInventory().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
