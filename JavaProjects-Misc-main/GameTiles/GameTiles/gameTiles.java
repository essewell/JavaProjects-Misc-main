
/**
 * gameTiles uses programTiles to initialize and activate multiple tiles (Buttons) in a program window.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;


public class gameTiles {
    programTiles tile1;
    programTiles tile2;
    programTiles tile3;
    programTiles tile4;
    public gameTiles() {
        JFrame window = new JFrame("GameTiles");
        JPanel thePanel = new JPanel(new GridLayout(1,4));
        TestConstructor2(thePanel);
        window.getContentPane().add(thePanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
    public void TestConstructor1(JPanel panel) {
      tile1 = new programTiles();
      tile2 = new programTiles();
      tile3 = new programTiles();
      tile4 = new programTiles();
      panel.add(tile1);
      panel.add(tile2);
      panel.add(tile3);
      panel.add(tile4);
    }
    public void TestConstructor2(JPanel panel) {
      tile1 = new programTiles(100,Color.GREEN);
      tile2 = new programTiles(100,Color.BLUE);
      tile3 = new programTiles(100,Color.RED);
      tile4 = new programTiles(100,Color.YELLOW);
      panel.add(tile1);
      panel.add(tile2);
      panel.add(tile3);
      
      panel.add(tile4);
    }
    public static void main(String [] args) {
      new gameTiles();
    }
}
