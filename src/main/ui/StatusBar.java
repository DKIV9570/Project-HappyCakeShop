package ui;

import javax.swing.*;
import java.awt.*;

//The status bar which shows the round remain and the fund
public class StatusBar extends JPanel {
    private static final String ROUND_TXT = "Round remain: ";
    private static final String FUNDS_TXT = "Funds: $";
    private static final int LBL_WIDTH = 250;
    private static final int LBL_HEIGHT = 30;
    private int fund;
    private int roundRemain;
    private final JLabel roundRemainLbl;
    private final JLabel fundsLbl;

    /*
    effects: sets the background colour and draws the initial labels;
            updates this with the initial round and funds of the game given
    */
    public StatusBar(GameMenu gameMenu) {
        setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(new Color(180, 180, 180));

        roundRemain = gameMenu.getRoundRemain();
        fund = gameMenu.getShop().getFunds();

        roundRemainLbl = new JLabel(ROUND_TXT + roundRemain);
        Font font = new Font("Arial Black", 0, 24);
        roundRemainLbl.setFont(font);
        roundRemainLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        fundsLbl = new JLabel(FUNDS_TXT + fund);
        fundsLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        fundsLbl.setFont(font);

        add(roundRemainLbl);
        add(Box.createHorizontalStrut(200));
        add(fundsLbl);
    }

    /*
     modifies: this
     effects: updates the remain round and funds of the game.
     */
    public void update(GameMenu gameMenu) {

        roundRemain = gameMenu.getRoundRemain();
        fund = gameMenu.getShop().getFunds();
        roundRemainLbl.setText(ROUND_TXT + roundRemain);
        fundsLbl.setText(FUNDS_TXT + fund);
        repaint();
    }

}
