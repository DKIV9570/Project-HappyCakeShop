package persistence;

import org.junit.jupiter.api.Test;
import ui.CSG;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
