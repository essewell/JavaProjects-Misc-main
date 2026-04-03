
/**
 * TheGame runs the the JFrame and initializes TheBoard class so that the game may be run.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//importing necessary packages
import java.awt.*;
import javax.swing.*;
import java.net.*;

public class TheGame extends JFrame{
    //extending the JFrame class as to use the involved methods
    
    /**
     * main method initializes the constructor, theFrame and the image for the window.
     */
    public static void main(String[] args) {
        JFrame theFrame = new TheGame();
        theFrame.setVisible(true);
        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        theFrame.setIconImage(icon);
    }
    /**
     * Constructor for objects of class TheGame
     */
    public TheGame() {
        super();
        initializeGame();
        //constructor intializes the method to run the game and intialize the game window
    }
    /**
     * intializeGame calls TheBoard and begins the process of booting up the game and window. 
     * It sets basic perameters for the window.
     */
    public void initializeGame() {
        add(new TheBoard());
        setResizable(true);
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

