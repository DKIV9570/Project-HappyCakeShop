package ui;

import javax.swing.*;
import java.awt.*;

//The command bar contains 8 buttons
public class CommandBar extends JPanel {
    private JButton showMaterials;
    private JButton showCakes;
    private JButton market;
    private JButton makeCake;
    private JButton nextTurn;
    private JButton save;
    private JButton load;
    private JButton exit;
    private JButton music;
    private final Dimension preferredSize = new Dimension(200,50);
    private final Font font = new Font("Arial Black",0,24);
    private GameMenu gameMenu;

    /*
     * EFFECTS: initialize the command bar
     */
    public CommandBar(GameMenu g) {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createTitledBorder("Commands"));

        gameMenu = g;
        init(layout);
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize all the buttons
     */
    public void init(GridBagLayout layout) {
        setShowMaterials(layout);
        setShowCakes(layout);
        setMarket(layout);
        setMakeCake(layout);
        setNextTurn(layout);
        setSave(layout);
        setLoad(layout);
        setExit(layout);
        setMusic(layout);

        this.add(showMaterials);
        this.add(showCakes);
        this.add(market);
        this.add(makeCake);
        this.add(nextTurn);
        this.add(save);
        this.add(load);
        this.add(exit);
        this.add(music);
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the showMaterial button
     */
    public void setShowMaterials(GridBagLayout layout) {
        showMaterials = new JButton("Show Materials");
        showMaterials.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(showMaterials, g);

        showMaterials.addActionListener(e -> gameMenu.showMaterials());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the showCakes button
     */
    public void setShowCakes(GridBagLayout layout) {
        showCakes = new JButton("Show Cakes");
        showCakes.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(showCakes, g);

        showCakes.addActionListener(e -> gameMenu.showCakes());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the market button
     */
    public void setMarket(GridBagLayout layout) {
        market = new JButton("Market");
        market.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(market, g);

        market.addActionListener(e -> gameMenu.market());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the makeCake button
     */
    public void setMakeCake(GridBagLayout layout) {
        makeCake = new JButton("Make Cake");
        makeCake.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(makeCake, g);

        makeCake.addActionListener(e -> gameMenu.makeCake());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the nextTurn button
     */
    public void setNextTurn(GridBagLayout layout) {
        nextTurn = new JButton("Next Turn");
        nextTurn.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(nextTurn, g);

        nextTurn.addActionListener(e -> gameMenu.nextTurn());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the save button
     */
    public void setSave(GridBagLayout layout) {
        save = new JButton("Save Game");
        save.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(save, g);

        save.addActionListener(e -> gameMenu.save());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the load button
     */
    public void setLoad(GridBagLayout layout) {
        load = new JButton("Load Game");
        load.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 6;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(load, g);

        load.addActionListener(e -> gameMenu.load());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the exit button
     */
    public void setExit(GridBagLayout layout) {
        exit = new JButton("Exit");
        exit.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(exit, g);

        exit.addActionListener(e -> gameMenu.exit());
    }

    /*
     * MODIFIES:this
     * EFFECTS: initialize the music button
     */
    private void setMusic(GridBagLayout layout) {
        music = new JButton("Play/Stop Music");
        music.setPreferredSize(preferredSize);
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0;
        g.gridy = 8;
        g.gridwidth = 1;
        g.gridheight = 1;
        g.weightx = 0.5;
        g.weighty = 0.5;
        layout.setConstraints(music, g);

        music.addActionListener(e -> gameMenu.changeMusic());

    }
}
