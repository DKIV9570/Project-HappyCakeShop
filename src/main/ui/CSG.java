package ui;

import exceptions.MaterialException;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
 * Represents a cake shop game.
 */
public class CSG {

    private CakeShop shop;                                              //The cake shop
    private Town town = new Town();                                     //The town
    private int totalRound;                                             //total round of the game
    private int currentRound;                                           //current round of the game
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/CSG.json";

    private Scanner input = new Scanner(System.in);                     //The input of player

    public CakeShop getShop() {
        return shop;
    }

    public Town getTown() {
        return town;
    }

    public int getTotalRound() {
        return totalRound;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    /*
     * EFFECTS: run new game
     */
    public CSG() {
        showGuide();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println();
        System.out.println("You want to start a new game or resume from last save?");
        System.out.println("input 1 to resume, any other key for new game");
        String chose = input.next();
        if (chose.equals("1")) {
            loadCSG();
            mainMenu();
        } else {
            newGame();
        }
    }

    /*
     * EFFECTS: create an CSG class for test, without run the game;
     */
    public CSG(int totalRound) {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        //The initial fund of the shop
        int initialFund = 1000;
        //initialize the cake shop
        this.shop = new CakeShop(initialFund,town.getMarket());
        this.totalRound = totalRound;
        currentRound = 1;
    }

    /*
     * MODIFIES:this
     * EFFECTS: set this shop with the given shop
     */
    public void setShop(CakeShop shop) {
        this.shop = shop;
    }

    /*
     * MODIFIES:this
     * EFFECTS: set this Town with the given Town
     */
    public void setTown(Town town) {
        this.town = town;
    }

    /*
     * MODIFIES:this
     * EFFECTS: set the total round with given value
     */
    public void setTotalRound(int totalRound) {
        this.totalRound = totalRound;
    }

    /*
     * MODIFIES:this
     * EFFECTS: set current round with given value
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    /*
     * EFFECTS: show the guide to the player
     */
    protected void showGuide() {
        System.out.println("Welcome to the Happy cake shop game");
        System.out.println("In this game, your can buy material from market, make cake and sell them");
        System.out.println("The goal of the game is to earn as much money as you can by the end of the game");
    }

    /*
     * MODIFIES: this
     * EFFECTS: ask the player to chose the total round of this game
     */
    protected void setRound() throws InputMismatchException {
        System.out.println("Now input the total round you prefer: you can input any integer you want");
        int roundInput = new Scanner(System.in).nextInt();
        this.totalRound = roundInput;
    }

    /*
     * MODIFIES:this
     * EFFECTS: start a new game
     */
    protected void newGame() {
        try {
            setRound();
        } catch (InputMismatchException e) {
            System.out.println("this is not a valid input, please input a number");
            newGame();
        }

        //The initial fund of the shop
        int initialFund = 1000;
        //initialize the cake shop
        this.shop = new CakeShop(initialFund,town.getMarket());

        currentRound = 1;
        //Game begin
        routine();
    }

    /*
     * EFFECTS: show the current round and open the main menu
     */
    protected void routine() {
        //print the Dividing line
        for (int i = 1; i <= 50; i++) {
            System.out.print("_");
        }
        System.out.println();

        if (currentRound <= totalRound) {
            System.out.println("This is the " + currentRound + " round of the game");
            mainMenu();
        } else {
            System.out.println("Game over, you earned $" + shop.getFunds() + " Good job!");
            System.out.println("Welcome back next time");
            System.exit(0);
        }
    }

    /*
     * EFFECTS: display the menu
     */
    protected void displayMenu() {
        System.out.println("Current fund: $" + shop.getFunds());
        System.out.println("Input the following number for corresponding operations");
        System.out.println("1 : Show the cake inventory");
        System.out.println("2 : Show the material inventory");
        System.out.println("3 : Buy material from market");
        System.out.println("4 : Make cakes and set price");
        System.out.println("5 : Next round (This will automatically sell all of your cakes in inventory)");
        System.out.println("6 : save current game progress");
        System.out.println("7 : load a game progress");
        System.out.println("8 : End game (this will exit from game)");
    }

    /*
     * MODIFIES:this
     * EFFECTS:handle the instructions inputted
     */
    protected void mainMenu() {
        displayMenu();
        String instruction = input.next().toLowerCase();
        switch (instruction) {
            case "1":showCakeInventory();
            case "2":showMaterialInventory(true);
            case "3":shopping();
                break;
            case "4":makeCakeMenu(true);
                break;
            case "5":
                shop.sellCake(town.getResidents());
                currentRound += 1;
                routine();
            case "6": saveCSG();
                mainMenu();
            case "7": loadCSG();
                mainMenu();
            case "8": exitGame();
            default: System.out.println("Not valid, please input from the instructions below");
                mainMenu();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: ask player if want to save, and then quit the game
     */
    protected void exitGame() {
        System.out.println("do you want to save your current progress?");
        System.out.println("1 for yes, rest key for no");
        String answer = input.next();
        if (answer.equals("1")) {
            saveCSG();
            System.out.println("Game saved, see you then ~");
        } else {
            System.out.println("Game over, see you next time!");
        }
        System.exit(0);
    }

    /*
     * EFFECTS: display the cake inventory
     */
    protected void showCakeInventory() {
        if (shop.getCakeInventory().isEmpty()) {
            System.out.println("Now we don't have any cake");
        } else {
            System.out.println("Now we have :");
            System.out.println("Name / Inventory / current price");
            for (String cakeName : shop.getCakeInventory().keySet()) {
                int inventory = shop.getCakeInventory().get(cakeName).getInventory();
                int price = shop.getCakeInventory().get(cakeName).getPrice();
                System.out.println(cakeName + " / " + inventory + " / $" + price);
            }
        }
        System.out.println();
        mainMenu();
    }

    /*
     * EFFECTS: display the material inventory
     */
    protected void showMaterialInventory(boolean call) {
        System.out.println("Now we have :");
        System.out.println("cake base : ");
        for (String name:shop.getBaseInventory().keySet()) {
            int amount = shop.getBaseInventory().get(name).getInventory();
            System.out.println(name + " : " + amount);
        }
        System.out.println("cream : ");
        for (String name:shop.getCreamInventory().keySet()) {
            int amount = shop.getCreamInventory().get(name).getInventory();
            System.out.println(name + " : " + amount);
        }
        System.out.println("topping : ");
        for (String name:shop.getToppingInventory().keySet()) {
            int amount = shop.getToppingInventory().get(name).getInventory();
            System.out.println(name + " : " + amount);
        }
        System.out.println();
        if (call) {
            mainMenu();
        }
    }

    /*
     * REQUIRES: input should be a number
     * MODIFIES: this
     * EFFECTS: chose the kind of material to buy.
     */
    protected void buyMaterial(String kind) {
        System.out.println("Which kind of " + kind + " you want to buy?");
        for (int i = 1; i <= town.getMarket().get(kind).size(); i++) {
            System.out.println("Input " + i + " for " + town.getMarket().get(kind).get(i - 1).getName());
        }
        int answer = input.nextInt() - 1;
        Material goodToBuy = null;
        if (answer <= town.getMarket().get(kind).size() - 1) {
            goodToBuy = town.getMarket().get(kind).get(answer);
        } else {
            System.out.println("This is an Invalid input");
            buyMaterial(kind);
        }

        choseAmountToBuy(goodToBuy);

        System.out.println("Input 1 to keep shopping, input any other number to go back to main menu");
        String decision = input.next().toLowerCase();
        if (decision.equals("1")) {
            shopping();
        } else {
            mainMenu();
        }
    }

    /*
     * REQUIRES: input should be a number
     * MODIFIES: this
     * EFFECTS: chose the amount to buy, and buy the material
     */
    protected void choseAmountToBuy(Material goodToBuy) {
        System.out.println("How many " + goodToBuy.getName() + " do you want to buy?");
        int numberToBuy = input.nextInt();
        if (numberToBuy * goodToBuy.getPrice() > shop.getFunds()) {
            System.out.println("Sorry, you don't have enough fund");
        } else {
            for (int i = 1; i <= numberToBuy; i++) {
                shop.buyMaterial(goodToBuy);
            }
            System.out.println("You have successfully purchased " + numberToBuy + " " + goodToBuy.getName());
        }
    }

    /*
     * EFFECTS: display the market
     */
    protected void displayMarket() {
        System.out.println("Welcome to the market, below are the goods available and their price");
        for (String kind : town.getMarket().keySet()) {
            System.out.println(kind + ":");
            for (Material material : town.getMarket().get(kind)) {
                System.out.println(material.getKind() + material.getSerialNumber() + "\t"
                        + material.getName() + ": $" + material.getPrice());
            }
        }
        System.out.println();
        System.out.println("You current have $" + shop.getFunds());
        System.out.println("What kind of material do you want to buy?");
        System.out.println("Input 1 for cake base");
        System.out.println("Input 2 for cream");
        System.out.println("Input 3 for topping");
        System.out.println("Input any other number to return main menu");
    }

    /*
     * MODIFIES: this
     * EFFECTS: buy materials from market
     */
    protected void shopping() {
        displayMarket();
        String instruction = input.next();
        switch (instruction) {
            case "1": buyMaterial("cake base");
                break;
            case "2": buyMaterial("cream");
                break;
            case "3": buyMaterial("topping");
                break;
            default: mainMenu();
        }
    }

    /*
     * EFFECTS: select the materials for making a cake and return it
     */
    protected Material select(String kind,Map<String,Material> materials) {
        System.out.println("Please select the " + kind);
        System.out.println("SerialNumber" + " / Name" + " / Inventory");
        List<Material> index = new ArrayList<>();
        for (String name: materials.keySet()) {
            System.out.println(materials.get(name).getSerialNumber()  + " / "
                    + name + " / " + materials.get(name).getInventory());
            index.add(materials.get(name));
        }
        System.out.println("Input the Serial Number for corresponding " + kind
                + ", input other number to return to main menu");
        int instruction = input.nextInt();
        if ((instruction == 1) || (instruction == 2) || (instruction == 3)) {
            return (index.get(instruction - 1));
        } else {
            mainMenu();
        }
        return null;
    }

    /*
     * EFFECTS: set the price for a kind of cake
     */
    protected int setPrice(String cakeMade) {
        int price;
        if (shop.getCakeInventory().get(cakeMade).getPrice() != -1) {
            System.out.println("Do you want to reset the price? 1 for YES, 2 for No");
            String answer = input.next();
            if (answer.equals("1")) {
                System.out.println("How much? Input an number");
                price = input.nextInt();
            } else {
                price = shop.getCakeInventory().get(cakeMade).getPrice();
            }
        } else {
            System.out.println("Please input an integer for the price of this kind of cake");
            price = input.nextInt();
        }
        shop.getCakeInventory().get(cakeMade).setPrice(price);
        return price;
    }

    /*
     * MODIFIES: this
     * EFFECTS: make cake and return true if any cake are made
     */
    protected void makeCake() {
        List<Material> used = new ArrayList<>();
        used.add(select("cake base",shop.getBaseInventory()));
        used.add(select("cream",shop.getCreamInventory()));
        used.add(select("topping",shop.getToppingInventory()));

        System.out.println("How many this kind of cake you want to make?");
        int number = input.nextInt();
        Cake cakeMade = new Cake(used.get(0), used.get(1), used.get(2));

        int result = shop.makeCake(used.get(0), used.get(1), used.get(2), -1, number);
        if (result != 0) {
            System.out.println("You do not have enough material");
            makeCakeMenu(false);
        }
        int price = setPrice(cakeMade.getName());

        System.out.println("You successfully made " + number + " " + cakeMade.getName() + " with price of " + price);
    }

    /*
     * MODIFIES: this
     * EFFECTS: use the chosen material to make the given number of cakes and set price for them
     */
    protected void makeCakeMenu(boolean first) {
        if (first) {
            System.out.println("Below is the materials you have");
            showMaterialInventory(false);
        }
        makeCake();

        System.out.println("input 1 to keep making cake, other to go back to main menu");
        String instruction = input.next();
        if (instruction.equals("1")) {
            makeCakeMenu(false);
        } else {
            mainMenu();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: saves the CSG to file, and return to mainMenu
     */
    private void saveCSG() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved this game" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: load the CSG from file, and return to mainMenu
     */
    private void loadCSG() {
        try {
            CSG csg = jsonReader.read(this);
            this.setShop(csg.getShop());
            this.setTown(csg.getTown());
            this.setCurrentRound(csg.getCurrentRound());
            this.setTotalRound(csg.getTotalRound());
            System.out.println("Loaded the game" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
