package ui;

import model.Material;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//The control bar of the options when making cake and marketing
public class ControlBar extends JPanel {
    private final Dimension preferredSize = new Dimension(200,50);
    private final Font font = new Font("Arial Black", Font.PLAIN,24);
    private JButton choice1;
    private JButton choice2;
    private JButton choice3;
    private JButton cancel;
    private JSpinner spinner;
    private JButton confirm;

    private final GameMenu gameMenu;
    private String status;
    private Material materialToBuy;
    private String kind;
    private List<String> selected =  Stream.of("", "", "").collect(Collectors.toList());
    private int amount = 0;

    public ControlBar(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createLoweredBevelBorder());

        init(layout);
        add(choice1);
        add(choice2);
        add(choice3);
        add(spinner);
        add(confirm);
        add(cancel);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMaterialToBuy(Material materialToBuy) {
        this.materialToBuy = materialToBuy;
    }

    /*
     * MODIFIES: this
     * EFFECTS: change the model of the spinner
     */
    public void changeSpinnerModel(SpinnerModel model) {
        spinner.setModel(model);
    }

    /*
     * MODIFIES: this
     * EFFECTS: initialize all the parts in control bar
     */
    private void init(GridBagLayout layout) {

        setChoice1(layout);
        setChoice2(layout);
        setChoice3(layout);
        setSpinner(layout);
        setConfirm(layout);
        setCancel(layout);
        muteChoices();
        muteSpinner();

    }

    /*
     * MODIFIES: this
     * EFFECTS: set the cancel button
     */
    private void setCancel(GridBagLayout layout) {
        cancel = new JButton("Cancel");
        cancel.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(cancel, g);
        cancel.addActionListener(e -> gameMenu.mainMenu(false));
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the confirm button
     */
    private void setConfirm(GridBagLayout layout) {
        confirm = new JButton("Confirm");
        confirm.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 3;
        g.gridy = 1;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(confirm, g);

        confirm.addActionListener(e -> {
            int amount = (int) spinner.getValue();
            if (status.equals("material")) {
                gameMenu.buyMaterial(materialToBuy,amount);
            } else if (status.equals("makeCake")) {
                this.amount = amount;
                gameMenu.makingCake(selected,amount);
            } else if (status.equals("setPrice")) {
                gameMenu.setPrice(selected,amount);
            }
        });
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the spinner
     */
    private void setSpinner(GridBagLayout layout) {
        spinner = new JSpinner();
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 3;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        g.fill = GridBagConstraints.BOTH;
        spinner.setFont(font);
        layout.setConstraints(spinner, g);

    }

    /*
     * MODIFIES: this
     * EFFECTS: set the choice3 button
     */
    private void setChoice3(GridBagLayout layout) {
        choice3 = new JButton("3");
        choice3.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 2;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(choice3, g);

        choice3.addActionListener(e ->
                actionOfChoice("topping",3,"Medium base","Blueberry cream","Nuts topping"));
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the choice2 button
     */
    private void setChoice2(GridBagLayout layout) {
        choice2 = new JButton("2");
        choice2.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 1;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(choice2, g);

        choice2.addActionListener(e ->
                actionOfChoice("cream",2,"Hard base","Chocolate cream","Banana topping"));
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the choice1 button
     */
    public void setChoice1(GridBagLayout layout) {
        choice1 = new JButton("1");
        choice1.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(choice1, g);

        choice1.addActionListener(e ->
                actionOfChoice("cake base",1,"Soft base","Pure cream","Apple topping"));
    }

    /*
     * MODIFIES: this
     * EFFECTS: the action method, can be used in choice1 choice2 and choice3
     */
    private void actionOfChoice(String kind,int serial,String mc1, String mc2, String mc3) {
        switch (status) {
            case "shopping-1":
                this.kind = kind;
                gameMenu.choseMaterial(kind);
                break;
            case "shopping-2":
                gameMenu.choseAmountToBuy(this.kind, serial);
                break;
            case "makingCake-1":
                selected.set(0, mc1);
                status = "makingCake-2";
                gameMenu.select("cream", gameMenu.getShop().getCreamInventory());
                break;
            case "makingCake-2":
                selected.set(1, mc2);
                status = "makingCake-3";
                gameMenu.select("topping", gameMenu.getShop().getToppingInventory());
                break;
            case "makingCake-3":
                selected.set(2, mc3);
                gameMenu.choseAmountToMake(selected);
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: hide all the choice button
     */
    public void muteChoices() {
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        cancel.setVisible(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: show the choice buttons
     */
    public void activateChoices() {
        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        cancel.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: hide spinner and confirm
     */
    public void muteSpinner() {
        spinner.setVisible(false);
        confirm.setVisible(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: show spinner and confirm
     */
    public void activateSpinner() {
        spinner.setVisible(true);
        confirm.setVisible(true);
    }

}
