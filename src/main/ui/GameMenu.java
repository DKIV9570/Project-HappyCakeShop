package ui;

import model.Cake;
import model.CakeShop;
import model.Material;
import model.Town;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//The main menu of the cake shop game
public class GameMenu extends JFrame {
    private static final String JSON_STORE = "./data/Happy Cake Shop.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private StatusBar statusBar;
    private Displayer displayer;
    private CommandBar commandBar;
    private ControlBar controlBar;
    private Music music = new Music();
    private boolean playing;

    private CakeShop shop;                               //The cake shop
    private Town town = new Town();                      //The town
    private int roundRemain;                             //Remain round of the game

    /*
     EFFECTS: initialize the game menu
    */
    public GameMenu() {
        super("Happy Cake Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Dimension dimension = new Dimension(900, 900);
        setPreferredSize(dimension);

        shop = new CakeShop(1000,town.getMarket());

        statusBar = new StatusBar(this);
        add(statusBar,BorderLayout.NORTH);
        displayer = new Displayer();
        add(displayer,BorderLayout.CENTER);
        commandBar = new CommandBar(this);
        add(commandBar,BorderLayout.EAST);
        controlBar = new ControlBar(this);
        add(controlBar,BorderLayout.SOUTH);
        pack();
        centreOnScreen();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUp();
        music.loop();
        playing = true;

    }

    /*
     EFFECTS: initialize the game menu, only for test use
     */
    public GameMenu(int roundRemain) {
        shop = new CakeShop(1000,town.getMarket());
        this.roundRemain = roundRemain;
    }

    public int getRoundRemain() {
        return roundRemain;
    }

    public CakeShop getShop() {
        return shop;
    }

    public Town getTown() {
        return town;
    }

    public void setShop(CakeShop shop) {
        this.shop = shop;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public void setRoundRemain(int roundRemain) {
        this.roundRemain = roundRemain;
    }

    /*
     * MODIFIES:this
     * EFFECTS: set up the total round with an positive integer
     */
    public void setUp() {
        displayer.welcome();
        try {
            roundRemain = Integer.parseInt(JOptionPane.showInputDialog("please input the total round you want"));
            if (roundRemain <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "This is not an valid input, please input an positive number");
            setUp();
        }
        statusBar.update(this);
        mainMenu(false);
    }

    /*
    MODIFIES: this
    EFFECTS:  location of frame is set so frame is centred on desktop
   */
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    /*
     * EFFECTS: display the material inventory
     */
    public void showMaterials() {
        displayer.showMaterials(shop);
    }

    /*
     * EFFECTS: display the cake inventory
     */
    public void showCakes() {
        displayer.showCakes(shop);
    }

    /*
     * MODIFIES: this
     * EFFECTS: run the market routine
     */
    public void market() {
        displayer.showMarket(this);
        commandBar.setVisible(false);
        controlBar.setStatus("shopping-1");
        controlBar.activateChoices();
    }

    /*
     * MODIFIES: this
     * EFFECTS: run the make cake routine
     */
    public void makeCake() {
        commandBar.setVisible(false);
        controlBar.setStatus("makingCake-1");
        controlBar.activateChoices();
        select("cake base",shop.getBaseInventory());

    }

    /*
     * MODIFIES: this
     * EFFECTS: end current turn, sell all the cakes in inventory
     */
    public void nextTurn() {
        shop.sellCake(town.getResidents());
        roundRemain -= 1;
        statusBar.update(this);
        if (roundRemain <= 0) {
            displayer.gameEnd(this);
            JOptionPane.showMessageDialog(this,"Game Over");
            System.exit(0);
        } else {
            displayer.nextTurn(this);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: save current game progress and show message
     */
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            String s1 = "Successfully Saved this game" + " to \n" + JSON_STORE;
            displayer.showMessage(s1);
        } catch (FileNotFoundException e) {
            String s2 = "Unable to write to file: \n" + JSON_STORE;
            displayer.showMessage(s2);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: load the game progress and show message
     */
    public void load() {
        try {
            GameMenu gameMenu = jsonReader.read(this);
            this.setShop(gameMenu.getShop());
            this.setTown(gameMenu.getTown());
            this.setRoundRemain(gameMenu.getRoundRemain());
            String l1 = "Successfully Loaded the game" + " from \n" + JSON_STORE;
            displayer.showMessage(l1);
        } catch (IOException e) {
            String l2 = "Unable to read from file: \n" + JSON_STORE;
            displayer.showMessage(l2);
        }
        statusBar.update(this);
    }

    /*
     * EFFECTS: ask user whether or not they want to save the game, then exit game
     */
    public void exit() {
        Object[] options = {"yes","no"};
        int response = JOptionPane.showOptionDialog(this,
                "Do you want to save your current game progress?","EXIT",JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (response == 0) {
            save();
        }
        JOptionPane.showMessageDialog(this,"See you next time~");
        System.exit(0);
    }

    /*
     * MODIFIES: this
     * EFFECTS: chose the kind of material to buy
     */
    public void choseMaterial(String kind) {
        displayer.choseMaterial(this,kind);
        controlBar.setStatus("shopping-2");
    }

    /*
     * MODIFIES: this
     * EFFECTS: chose the amount of material to buy
     */
    public void choseAmountToBuy(String kind,int serialNumber) {
        Material goodToBuy = town.getMarket().get(kind).get(serialNumber - 1);
        displayer.showMessage("How many " + goodToBuy.getName() + " do you want to buy?\n");
        controlBar.muteChoices();
        controlBar.activateSpinner();
        controlBar.setStatus("material");
        controlBar.setMaterialToBuy(goodToBuy);
        int maxAmount = shop.getFunds() / goodToBuy.getPrice();
        controlBar.changeSpinnerModel(new SpinnerNumberModel(0,0,maxAmount,1));
    }

    /*
     * MODIFIES: this
     * EFFECTS:  buy the chosen amount of chosen material
     */
    public void buyMaterial(Material material,int amount) {
        shop.buyMaterial(material,amount);
        String s = ("You have successfully purchased " + amount + " " + material.getName());
        displayer.showMessage(s);
        mainMenu(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: return to main menu, mute the control bar
     */
    public void mainMenu(boolean success) {
        if (!success) {
            displayer.showMessage("What to do next?");
        }
        statusBar.update(this);
        controlBar.muteChoices();
        controlBar.muteSpinner();
        commandBar.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  show the inventory of the given kind of material in the shop
     */
    public void select(String kind, Map<String,Material> materials) {
        StringBuilder s = new StringBuilder();
        s.append("Please select the ").append(kind).append("\n");
        s.append("SerialNumber" + " / Name" + " / Inventory\n\n");
        List<Material> index = new ArrayList<>();
        for (String name: materials.keySet()) {
            s.append(materials.get(name).getSerialNumber())
                    .append(" / ").append(name).append(" / ").append(materials.get(name).getInventory()).append("\n");
            index.add(materials.get(name));
        }
        s.append("\nChose the Serial Number for corresponding ")
                .append(kind).append("\nchose cancel to return to main menu\n");
        displayer.showMessage(s.toString());
    }

    /*
     * MODIFIES: this
     * EFFECTS:  chose amount of cake want to make
     */
    public void choseAmountToMake(List<String> selected) {

        Material cakeBase = shop.getBaseInventory().get(selected.get(0));
        Material cream = shop.getCreamInventory().get(selected.get(1));
        Material topping = shop.getToppingInventory().get(selected.get(2));
        int maxAmount = Math.max(Math.max(cakeBase.getInventory(), cream.getInventory()),topping.getInventory());
        controlBar.activateSpinner();
        controlBar.muteChoices();
        displayer.makeCake(cakeBase,cream,topping);
        controlBar.changeSpinnerModel(new SpinnerNumberModel(0, 0, maxAmount, 1));
        controlBar.setStatus("makeCake");
    }

    /*
     * MODIFIES: this
     * EFFECTS:  make the cake of given number and jump to set price
     */
    public void makingCake(List<String> selected, int amount) {
        Material cakeBase = shop.getBaseInventory().get(selected.get(0));
        Material cream = shop.getCreamInventory().get(selected.get(1));
        Material topping = shop.getToppingInventory().get(selected.get(2));
        if (amount > 0) {
            shop.makeCake(cakeBase.getName(), cream.getName(), topping.getName(), amount);
            controlBar.setStatus("setPrice");
            controlBar.changeSpinnerModel(new SpinnerNumberModel());
            displayer.showMessage("Please chose the price of this kind of cake");
        } else {
            displayer.showMessage("You do not have enough materials, why not buy some from the market?");
            mainMenu(true);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS:  set the price of the new cake made
     */
    public void setPrice(List<String> selected, int price,int amount) {
        Material cakeBase = shop.getBaseInventory().get(selected.get(0));
        Material cream = shop.getCreamInventory().get(selected.get(1));
        Material topping = shop.getToppingInventory().get(selected.get(2));
        Cake cake = new Cake(cakeBase,cream,topping);
        shop.getCakeInventory().get(cake.getName()).setPrice(price);
        displayer.successMade(this,cake);
        mainMenu(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS:  play/stop the music
     */
    public void changeMusic() {
        if (playing) {
            music.stop();
            playing = false;
        } else {
            music.loop();
            playing = true;
        }
    }
}
