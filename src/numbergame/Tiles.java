package numbergame;

import java.awt.Color;

public class Tiles {

    //constants
    public static final int PADDING = 10;
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;
    
    
    private int x, y, value;
    private boolean selected = false;
    
    private static final Color[] colors = 
    {   //0
        Color.gray, 
        //1
        Color.blue, 
        Color.red, 
        Color.green, 
        Color.CYAN,  
        //purple
        Color.decode("#f442b9"),
        //orange
        Color.decode("#ff9000"),
    };
    
    public static final int NUM_OF_COLORS = colors.length-1;
    

    public Tiles(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return colors[value];
    }

    public Color isSelected() {
        if (!selected) {
            return Color.white;
        } else {
            return Color.black;
        }
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
