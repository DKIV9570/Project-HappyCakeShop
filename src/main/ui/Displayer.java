package ui;

import model.Cake;
import model.CakeShop;
import model.Material;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

//The scrollable displayer
public class Displayer extends JScrollPane {
    private final JTextArea textArea = new JTextArea();

    /*
     * EFFECTS: set up the displayer
     */
    public Displayer() {
        setBorder(BorderFactory.createLoweredBevelBorder());
        textArea.setFont(new Font("Unispace", Font.PLAIN,24));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        setViewportView(textArea);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the welcome text to the player
     */
    public void welcome() {
        String welcome = "Welcome to the Happy cake shop game\n"
                + "In this game, your can buy material from market, make cake and sell them\n"
                + "The goal of the game is to earn as much money as you can by the end of the game\n\n"
                + "Now please input the total rounds in this game";
        textArea.setText(welcome);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the material inventory to the player
     */
    public void showMaterials(CakeShop shop) {
        StringBuilder materials = new StringBuilder();
        materials.append("Now we have :\n");
        materials.append("\ncake base : \n");
        for (String name:shop.getBaseInventory().keySet()) {
            int amount = shop.getBaseInventory().get(name).getInventory();
            materials.append(name).append(" : ").append(amount).append("\n");
        }
        materials.append("\ncream : \n");
        for (String name:shop.getCreamInventory().keySet()) {
            int amount = shop.getCreamInventory().get(name).getInventory();
            materials.append(name).append(" : ").append(amount).append("\n");
        }
        materials.append("\ntopping : \n");
        for (String name:shop.getToppingInventory().keySet()) {
            int amount = shop.getToppingInventory().get(name).getInventory();
            materials.append(name).append(" : ").append(amount).append("\n");
        }
        textArea.setText(materials.toString());
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the cake inventory to the player
     */
    public void showCakes(CakeShop shop) {
        StringBuilder cakes = new StringBuilder();
        if (shop.getCakeInventory().isEmpty()) {
            cakes = new StringBuilder("Now we don't have any cake");
        } else {
            cakes.append("Now we have :\n");
            cakes.append("Name / Inventory / current price\n");
            for (String cakeName : shop.getCakeInventory().keySet()) {
                int inventory = shop.getCakeInventory().get(cakeName).getInventory();
                int price = shop.getCakeInventory().get(cakeName).getPrice();
                cakes.append(cakeName).append(" / ").append(inventory).append(" / $").append(price).append("\n");
            }
        }
        textArea.setText(cakes.toString());
    }

    /*
     * MODIFIES:this
     * EFFECTS: show message
     */
    public void showMessage(String s) {
        textArea.setText(s);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show message of game end
     */
    public void gameEnd(GameMenu g) {
        String s = "The game is ended\n You have earned $" + g.getShop().getFunds()
                + "\n Good Job!\n See you next time";
        textArea.setText(s);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show message of next turn begins
     */
    public void nextTurn(GameMenu gameMenu) {
        String s = "You have " + gameMenu.getRoundRemain() + " rounds left\n";
        textArea.setText(s);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show market
     */
    public void showMarket(GameMenu gameMenu) {
        StringBuilder s = new StringBuilder();
        s.append("Welcome to the market, below are the goods available and their price\n");
        for (String kind : gameMenu.getTown().getMarket().keySet()) {
            s.append("\n").append(kind).append(":\n");
            for (Material material : gameMenu.getTown().getMarket().get(kind)) {
                s.append(material.getKind()).append(material.getSerialNumber()).append("\t")
                        .append(material.getName()).append(": $").append(material.getPrice()).append("\n");
            }
        }

        s.append("\nYou current have $").append(gameMenu.getShop().getFunds()).append("\n");
        s.append("What kind of material do you want to buy?\n\n");
        s.append("chose 1 for cake base\n");
        s.append("chose 2 for cream\n");
        s.append("chose 3 for topping\n");
        s.append("chose cancel to return main menu\n");

        textArea.setText(s.toString());
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the materials of chosen kind
     */
    public void choseMaterial(GameMenu gameMenu, String kind) {
        StringBuilder s = new StringBuilder();
        s.append("Which kind of ").append(kind).append(" you want to buy?\n");
        for (Material m: gameMenu.getTown().getMarket().get(kind)) {
            s.append("Chose ").append(m.getSerialNumber()).append(" for ").append(m.getName()).append("\n");
        }
        textArea.setText(s.toString());
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the text of chose the amount of the cake
     */
    public void makeCake(Material cakeBase, Material cream, Material topping) {
        String s = "";
        String name = " \" " + cakeBase.getName() + "/" + cream.getName() + "/" + topping.getName() + " cake \" ";
        s += "How many of this " + name + " you want to make?";
        textArea.setText(s);
    }

    /*
     * MODIFIES:this
     * EFFECTS: show the text of successfully made the cake with given amount and price
     */
    public void successMade(GameMenu gameMenu, Cake cake) {
        String s = "";
        int inventory = gameMenu.getShop().getCakeInventory().get(cake.getName()).getInventory();
        int price = gameMenu.getShop().getCakeInventory().get(cake.getName()).getPrice();
        s += "You have successfully made\n"
                + inventory + cake.getName() + "\n"
                + "with the price of: $" + price;
        textArea.setText(s);
    }

    //public void select(String kind, Map<String,Material> materials) { }
}
