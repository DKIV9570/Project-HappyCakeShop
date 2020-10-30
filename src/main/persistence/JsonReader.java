package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.CSG;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/*
This is a JsonReader to read json from file and get CSG
referenced some design idea in the CPSC210.JsonSerializationDemo project
*/
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CSG from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CSG read(CSG csg) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCSG(jsonObject,csg);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CSG from JSON object and returns it
    private CSG parseCSG(JSONObject jsonObject,CSG csg) {
        //String name = jsonObject.getString("name");
        getEnvironment(csg, jsonObject);
        return csg;
    }

    // MODIFIES: CSG
    // EFFECTS: parses the environment variables from JSON object and imply them to CSG
    private void getEnvironment(CSG csg, JSONObject jsonObject) {
        JSONObject jsonShop = jsonObject.getJSONObject("shop");
        JSONObject jsonTown = jsonObject.getJSONObject("town");
        int jsonTR = jsonObject.getInt("totalRound");
        int jsonCR = jsonObject.getInt("currentRound");

        csg.setShop(getShop(jsonObject));
        csg.setTown(getTown(jsonObject));

        csg.setTotalRound(jsonTR);
        csg.setCurrentRound(jsonCR);
    }

    // EFFECTS: parses the town from JSON object and return it
    private Town getTown(JSONObject jsonObject) {
        Town town  = new Town();
        JSONObject jsonTown = jsonObject.getJSONObject("town");

        JSONArray jsonResidents = jsonTown.getJSONArray("residents");
        List<Resident> residents = new LinkedList<>();
        for (Object json : jsonResidents) {
            JSONObject nextResident = (JSONObject) json;
            addResident(residents, nextResident);
        }
        town.setResidents(residents);

        Map<String, List<Material>> market = new LinkedHashMap<>();
        JSONObject jsonMarket = jsonTown.getJSONObject("market");
        for (String key: jsonMarket.keySet()) {
            List<Material> materials = new LinkedList<>();
            for (Object json : jsonMarket.getJSONArray(key)) {
                materials.add(getMaterial(json));
            }
            market.put(key, materials);
        }
        town.setMarket(market);

        return town;
    }

    // MODIFIES: town
    // EFFECTS: parses resident from JSON object and adds it to town
    private void addResident(List<Resident> residents, JSONObject jsonObject) {
        int purchasePower = jsonObject.getInt("purchasePower");
        JSONObject jsonFavors = jsonObject.getJSONObject("favors");
        Map<String,Float> favors = new LinkedHashMap<>();
        for (String key: jsonFavors.keySet()) {
            favors.put(key,jsonFavors.getFloat(key));
        }

        Resident resident = new Resident(purchasePower, favors);
        residents.add(resident);
    }

    // EFFECTS: parses a material from JSON object and returns it
    public Material getMaterial(Object json) {
        JSONObject jsonMaterial = (JSONObject) json;
        String name = jsonMaterial.getString("name");
        int price = jsonMaterial.getInt("price");
        String kind = jsonMaterial.getString("kind");
        int serialNumber = jsonMaterial.getInt("serialNumber");
        int inventory = jsonMaterial.getInt("inventory");

        Material material = new Material(name,price,kind,serialNumber);
        material.setInventory(inventory);
        return material;
    }

    // EFFECTS: parses the shop from JSON object and return it
    private CakeShop getShop(JSONObject jsonObject) {

        JSONObject jsonShop = jsonObject.getJSONObject("shop");
        Map<String,Material> baseInventory = new LinkedHashMap<>();
        JSONArray jsonBases = jsonShop.getJSONArray("baseInventory");
        getMaterials(jsonBases,baseInventory);
        Map<String,Material> creamInventory = new LinkedHashMap<>();
        JSONArray jsonCreams = jsonShop.getJSONArray("creamInventory");
        getMaterials(jsonCreams,creamInventory);
        Map<String,Material> toppingInventory = new LinkedHashMap<>();
        JSONArray jsonToppings = jsonShop.getJSONArray("toppingInventory");
        getMaterials(jsonToppings,toppingInventory);
        Map<String, Cake> cakeInventory = new LinkedHashMap<>();
        JSONArray jsonCakes = jsonShop.getJSONArray("cakeInventory");
        getCakes(jsonCakes,cakeInventory);
        int funds = jsonShop.getInt("funds");

        CakeShop cakeshop = new CakeShop(funds,baseInventory,creamInventory,toppingInventory,cakeInventory);
        return cakeshop;
    }

    // MODIFIES: map
    // EFFECTS: parses materials from JSON object and adds it to map
    public void getMaterials(JSONArray jsonArray,Map<String,Material> map) {
        for (Object json : jsonArray) {
            Material material = getMaterial(json);
            map.put(material.getName(),material);
        }
    }

    // MODIFIES: map
    // EFFECTS: parses cakes from JSON object and adds it to map
    public void getCakes(JSONArray jsonArray,Map<String,Cake> map) {
        for (Object json : jsonArray) {
            Cake cake = getCake(json);
            map.put(cake.getName(),cake);
        }
    }

    // EFFECTS: parses a cake from JSON object and return it
    public Cake getCake(Object json) {
        JSONObject jsonCake = (JSONObject) json;
        String name = jsonCake.getString("name");
        Material cakeBase = getMaterial(jsonCake.getJSONObject("cakeBase"));
        Material cream = getMaterial(jsonCake.getJSONObject("cream"));
        Material topping = getMaterial(jsonCake.getJSONObject("topping"));
        int price = jsonCake.getInt("price");
        int inventory = jsonCake.getInt("inventory");

        Cake cake = new Cake(cakeBase,cream,topping);
        cake.setInventory(inventory);
        cake.setPrice(price);
        return cake;
    }
}
