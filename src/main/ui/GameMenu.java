package ui;

import com.sun.org.apache.xpath.internal.operations.String;
import model.CakeShop;
import model.Town;

import javax.swing.*;
import java.awt.*;

//The main menu of the cake shop game
public class GameMenu extends JFrame {
    private StatusBar statusBar;
    private Displayer gameBody;
    private CommandBar commandBar;

    private CakeShop shop;                                              //The cake shop
    private Town town = new Town();                                     //The town
    private int totalRound;                                             //total round of the game
    private int currentRound;                                           //current round of the game

    private Dimension dimension = new Dimension(900,900);

    public GameMenu() {
        super("Happy Cake Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setPreferredSize(dimension);

        shop = new CakeShop(1000,town.getMarket());

        statusBar = new StatusBar(shop,1);
        add(statusBar,BorderLayout.NORTH);

        gameBody = new Displayer();
        add(gameBody,BorderLayout.CENTER);

        commandBar = new CommandBar();
        add(commandBar,BorderLayout.EAST);
        pack();
        //setLocationRelativeTo(null);
        centreOnScreen();

    }



    //The chose dialog
    public void choseDialog() {
        Object[] options = {"1","2","3"};
        int response = JOptionPane.showOptionDialog(this,
                "这是个选项对话框，用户可以选择自己的按钮的个数", "选项对话框标题",JOptionPane.YES_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (response == 0) {
            System.out.println("you chose 1");
        } else if (response == 1) {
            System.out.println("you chose 2");
        } else if (response == 2) {
            System.out.println("you chose 3");
        }

    }


    /*
     modifies: this
     effects:  location of frame is set so frame is centred on desktop
    */
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }


}
