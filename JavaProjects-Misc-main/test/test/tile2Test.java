import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;


public class tile2Test {
    tile2 tile1;
    tile2 tile2;
    tile2 tile3;
    tile2 tile4;
    public tile2Test() {
        JFrame window = new JFrame("Game Tiles 2");
        JPanel thePanel = new JPanel(new GridLayout(1,4));
        TestConstructor2(thePanel);
        window.getContentPane().add(thePanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
    public void TestConstructor1(JPanel panel) {
      tile1 = new tile2();
      tile2 = new tile2();
      tile3 = new tile2();
      tile4 = new tile2();
      panel.add(tile1);
      panel.add(tile2);
      panel.add(tile3);
      panel.add(tile4);
    }
    public void TestConstructor2(JPanel panel) {
      tile1 = new tile2(100,Color.GREEN);
      tile2 = new tile2(100,Color.BLUE);
      tile3 = new tile2(100,Color.RED);
      tile4 = new tile2(100,Color.YELLOW);
      panel.add(tile1);
      panel.add(tile2);
      panel.add(tile3);
      panel.add(tile4);
    }
    public static void main(String [] args) {
      new tile2Test();
    }
}
