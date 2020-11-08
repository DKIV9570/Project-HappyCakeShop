package ui;

import model.CakeShop;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private static final String ROUND_TXT = "Current round: ";
    private static final String FUNDS_TXT = "Funds: ";
    private static final int LBL_WIDTH = 250;
    private static final int LBL_HEIGHT = 30;
    private CakeShop shop;
    private int currentRound;
    private JLabel currentRoundLbl;
    private JLabel fundsLbl;
    private Font font = new Font("Arial Black",0,24);

    // effects: sets the background colour and draws the initial labels;
    //          updates this with the current round and score of the game given
    public StatusBar(CakeShop cakeShop, int currentRound) {
        this.currentRound = currentRound;
        this.shop = cakeShop;

        setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(new Color(180, 180, 180));

        currentRoundLbl = new JLabel(ROUND_TXT + currentRound);
        currentRoundLbl.setFont(font);
        currentRoundLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        fundsLbl = new JLabel(FUNDS_TXT + shop.getFunds());
        fundsLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        fundsLbl.setFont(font);

        add(currentRoundLbl);
        add(Box.createHorizontalStrut(200));
        add(fundsLbl);
    }

    // modifies: this
    // effects:  updates the current round and fund of the game.
    public void update() {
        currentRoundLbl.setText(ROUND_TXT + currentRound);
        fundsLbl.setText(FUNDS_TXT + shop.getFunds());
        repaint();
    }



}
