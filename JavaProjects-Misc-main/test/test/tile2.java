/**
 * GameTiles is a test of JButtons and their modifiable characteristics.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
public class tile2 extends JLabel implements MouseListener{
    private int tileWidth;
    private Dimension tileSize;
    private Color hoverAppearance = Color.BLUE;
    private Color clickAppearance = Color.RED;
    private Color pressAppearance = Color.GREEN;
    private Color background=Color.BLACK;
    private final int DEFAULT_TILE_WIDTH = 128;
    private final int MIN_TILE_WIDTH = 128;
    public tile2() {
        this.addMouseListener(this);
        this.tileWidth = this.DEFAULT_TILE_WIDTH;
        tileSize = new Dimension(this.tileWidth,this.tileWidth);
        this.setPreferredSize(tileSize);
        this.setBackground(Color.LIGHT_GRAY);
        this.setHoverAppearance(Color.BLACK);
        this.setClickAppearance(Color.BLUE);
        this.setPressAppearance(Color.RED);
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY.darker(), 3));
        this.setOpaque(true);
    }
    public tile2(int sideWidth, Color theColor) {
        this.addMouseListener(this);
        background = theColor;
        this.setHoverAppearance(Color.GREEN);
        this.setClickAppearance(Color.RED);
        this.setPressAppearance(Color.RED);
        this.setTileSize(sideWidth);
        this.setTileColor(theColor);
        this.setOpaque(true);
    }
    public int getTileWidth() {
      return this.tileWidth;
    }    
    public void setTileSize(int sideWidth) {
        if (sideWidth < this.MIN_TILE_WIDTH) {
          this.tileWidth = this.MIN_TILE_WIDTH;
        }
        else {
          this.tileWidth = sideWidth;
        }
        this.tileSize = new Dimension(this.tileWidth, this.tileWidth);
        this.setPreferredSize(this.tileSize);
    }
    public void setTileColor(Color theColor) {
        this.setBackground(theColor);
        this.setBorder(BorderFactory.createLineBorder(theColor.darker(), 3));
    }
    public void mouseEntered(MouseEvent event) {
        this.setBackground(hoverAppearance);
        this.setBorder(new LineBorder(hoverAppearance, 3));
    }
    public void mouseExited(MouseEvent event) {
        this.setTileColor(background);
    }
    public void setHoverAppearance(Color theColor) {
        this.hoverAppearance = theColor;
    }
    public void setClickAppearance(Color theColor) {
        this.clickAppearance = theColor;
    }
    public void setPressAppearance(Color theColor) {
        this.pressAppearance = theColor;
    }
    public void mouseClicked(MouseEvent event) {
        this.setBackground(clickAppearance);
        this.setBorder(new LineBorder(clickAppearance, 3));
    }
    public void mouseReleased(MouseEvent event) {
        this.setBackground(hoverAppearance);
        this.setBorder(new LineBorder(hoverAppearance, 3));
    }
    public void mousePressed(MouseEvent event) {
        this.setBackground(pressAppearance);
        this.setBorder(new LineBorder(pressAppearance, 3));
    }
    
}
