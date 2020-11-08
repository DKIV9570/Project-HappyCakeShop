package ui;

import javax.swing.*;
import java.awt.*;

public class CommandBar extends JPanel {
    private JButton showMaterials;
    private JButton showCakes;
    private JButton market;
    private JButton makeCake;
    private JButton nextTurn;
    private JButton save;
    private JButton load;
    private JButton exit;
    private Dimension preferredSize = new Dimension(200,50);
    private Font font = new Font("Arial Black",0,24);

    public CommandBar() {

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createTitledBorder("Commands"));

        init(layout);
    }

    public void init(GridBagLayout layout) {
        setShowMaterials(layout);
        setShowCakes(layout);
        setMarket(layout);
        setMakeCake(layout);
        setNextTurn(layout);
        setSave(layout);
        setLoad(layout);
        setExit(layout);

        this.add(showMaterials);
        this.add(showCakes);
        this.add(market);
        this.add(makeCake);
        this.add(nextTurn);
        this.add(save);
        this.add(load);
        this.add(exit);
    }

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
    }

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
    }

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
    }

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
    }

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
    }

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
    }

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
    }

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
    }

}
