package numbergame;

import java.awt.Color;

public class Tiles {

    //constants
    public static final int PADDING = 10;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int tile_outline_thickness = 3;
    
    
    private int x, y, value;
    private boolean selected;
    private boolean removed;
    
    private static final Color[] colors = 
    {   //0
        Color.black, 
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
        selected = false;
        removed = false;
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
        if(removed)
        return colors[0];
        else
            return colors[value];
    }

    public Color isSelected() {
        if (!selected || removed) {
            return Color.black;
        } else {
            return Color.white;
        }
    }
    
    public boolean getRemoved(){
        return removed;
    }
    
    public void setRemoved(){
        this.removed=true;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
