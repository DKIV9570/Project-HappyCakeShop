package persistence;

import org.json.JSONObject;
import ui.CSG;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
This is a JsonWriter to write a CSG class to json and save it to file
referenced some design idea in the CPSC210.JsonSerializationDemo project
*/
public class JsonWriter {
    private static final int TAB = 5;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of CSG to file
    public void write(CSG csg) {
        JSONObject jsonCSG = new JSONObject();
        JSONObject jsonTown = csg.getTown().toJson();
        JSONObject jsonShop = csg.getShop().toJson();
        jsonCSG.put("totalRound",csg.getTotalRound());
        jsonCSG.put("currentRound",csg.getCurrentRound());
        jsonCSG.put("shop",jsonShop);
        jsonCSG.put("town",jsonTown);
        saveToFile(jsonCSG.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
