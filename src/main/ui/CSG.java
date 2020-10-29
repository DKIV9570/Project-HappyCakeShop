package ui;

import model.Cake;
import model.CakeShop;
import model.Material;
import model.Resident;

import java.util.*;

/*
 * Represents a cake shop game.
 */
public class CSG {

    private CakeShop shop;                                              //The cake shop
    private Map<String,List<Material>> market = new LinkedHashMap<>();  //The market
    private List<Resident> town = new ArrayList<>();                    //The town
    private int totalRound;                                             //total round of the game
    private int currentRound;                                           //current round of the game

    private Scanner input = new Scanner(System.in);                     //The input of player


    /*
     * EFFECTS: run new game
     */
    public CSG() {
        newGame();
    }

    /*
     * EFFECTS: add a kind of material to the market
     */
    protected void initializeMarket(List<String> kind, String name) {
        List<Material> listOfKind = new ArrayList<>();
        for (int i = 0; i < kind.size(); i++) {
            int price = (int) ((Math.random() * 19) + 1);
            Material material = new Material(kind.get(i),price,name,i + 1);
            listOfKind.add(material);
        }
        market.put(name,listOfKind);
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the environment of the game
     */
    protected void initialize() {
        List<String> bases = Arrays.asList("Soft base","Hard base","Medium base");
        List<String> creams = Arrays.asList("Pure cream","Chocolate cream","Blueberry cream");
        List<String> toppings = Arrays.asList("Apple topping","Banana topping","Nuts topping");

        //initialize the market
        initializeMarket(bases,"cake base");
        initializeMarket(creams,"cream");
        initializeMarket(toppings,"topping");

        //initialize the residents of the town
        //The population of the town
        int population = 500;
        for (int i = 1; i <= population; i++) {
            Resident resident = new Resident(market);
            town.add(resident);
        }
        currentRound = 1;
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
    protected void setRound() {
        System.out.println("Now input the total round you prefer: you can input any integer you want");
        int roundInput = input.nextInt();
        this.totalRound = roundInput;
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize and run the game
     */
    protected void newGame() {
        initialize();
        showGuide();
        setRound();

        //The initial fund of the shop
        int initialFund = 1000;
        //initialize the cake shop
        this.shop = new CakeShop(initialFund,market);

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
        System.out.println("6 : End game (this will make the game over)");
    }

    /*
     * MODIFIES:this
     * EFFECTS:handle the instructions inputted
     */
    protected void mainMenu() {
        displayMenu();
        String instruction = input.next();
        instruction = instruction.toLowerCase();
        switch (instruction) {
            case "1":showCakeInventory();
                break;
            case "2":showMaterialInventory(true);
                break;
            case "3":shopping();
                break;
            case "4":makeCakeMenu(true);
                break;
            case "5":
                shop.sellCake(town);
                currentRound += 1;
                routine();
            case "6":
                System.out.println("Game over, welcome next time");
                System.exit(0);
            default:
                System.out.println("Not valid, please input from the instructions below");
                mainMenu();
        }
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
        for (Material material:shop.getBaseInventory().keySet()) {
            int amount = shop.getBaseInventory().get(material);
            System.out.println(material.getName() + " : " + amount);
        }
        System.out.println("cream : ");
        for (Material material:shop.getCreamInventory().keySet()) {
            int amount = shop.getCreamInventory().get(material);
            System.out.println(material.getName() + " : " + amount);
        }
        System.out.println("topping : ");
        for (Material material:shop.getToppingInventory().keySet()) {
            int amount = shop.getToppingInventory().get(material);
            System.out.println(material.getName() + " : " + amount);
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
        for (int i = 1; i <= market.get(kind).size(); i++) {
            System.out.println("Input " + i + " for " + market.get(kind).get(i - 1).getName());
        }
        int answer = input.nextInt() - 1;
        Material goodToBuy = null;
        if (answer <= market.get(kind).size() - 1) {
            goodToBuy = market.get(kind).get(answer);
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
        for (String kind : market.keySet()) {
            System.out.println(kind + ":");
            for (Material material : market.get(kind)) {
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
    protected Material select(String kind,Map<Material,Integer> materials) {
        System.out.println("Please select the " + kind);
        System.out.println("SerialNumber" + " / Name" + " / Inventory");
        List<Material> index = new ArrayList<>();
        for (Material material: materials.keySet()) {
            System.out.println(material.getSerialNumber()  + " / "
                    + material.getName() + " / " + materials.get(material));
            index.add(material);
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
        if (shop.getCakeInventory().containsKey(cakeMade)) {
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
        return price;
    }

    /*
     * MODIFIES: this
     * EFFECTS: make cake and return true if any cake are made
     */
    protected boolean makeCake() {
        List<Material> used = new ArrayList<>();
        used.add(select("cake base",shop.getBaseInventory()));
        used.add(select("cream",shop.getCreamInventory()));
        used.add(select("topping",shop.getToppingInventory()));

        System.out.println("How many this kind of cake you want to make?");
        int number = input.nextInt();
        if (shop.getBaseInventory().get(used.get(0)) >= number
                && shop.getCreamInventory().get(used.get(1)) >= number
                && shop.getToppingInventory().get(used.get(2)) >= number) {
            Cake cakeMade = new Cake(used.get(0), used.get(1), used.get(2));
            int price = setPrice(cakeMade.getName());
            shop.makeCake(used.get(0), used.get(1), used.get(2), price, number);
            System.out.println("You successfully made " + number + " " + cakeMade.getName()
                    + " with price of " + price);
            return true;
        } else {
            return false;
        }
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
        boolean success = makeCake();
        if (success) {
            System.out.println("input 1 to keep making cake, other number to go back to main menu");
            int instruction = input.nextInt();
            if (instruction == 1) {
                makeCakeMenu(false);
            } else {
                mainMenu();
            }
        } else {
            System.out.println("Sorry, you don't have enough material");
            makeCakeMenu(false);
        }
    }
}
