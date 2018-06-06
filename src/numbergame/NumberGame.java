package numbergame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Yongcong
 */
public class NumberGame extends JPanel implements MouseListener {

    private int width;
    private int height;
    private GameBoard game_board;
    private int tile_outline_thickness = 2;
    private ArrayList<Tiles> selected_tiles = new ArrayList<Tiles>();

    public NumberGame(int width, int height) {
        this.width = width;
        this.height = height;
        game_board = new GameBoard(width, height);
        addMouseListener(this);
        setUpBoard();
    }

    public void setUpBoard() {
        JFrame f = new JFrame();
        f.add(this);
        f.setSize(game_board.getWidth() + 15, game_board.getHeight() + 40);
        f.setTitle("Number Game");
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, game_board.getWidth(), game_board.getHeight());
        drawBoard(game_board, g);
    }

    public void drawBoard(GameBoard gameboard, Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                Tiles tile = gameboard.getTiles(i,k);

                int x = tile.getX();
                int y = tile.getY();

                int outlineX = x - tile_outline_thickness;
                int outlineY = y - tile_outline_thickness;
                int outLineWidth = Tiles.TILE_WIDTH + 2 * tile_outline_thickness;
                int outLineHeight = Tiles.TILE_HEIGHT + 2 * tile_outline_thickness;
                
                g.setColor(tile.isSelected());
                g.fillRect(outlineX, outlineY, outLineWidth, outLineHeight);
                
                g.setColor(gameboard.getTiles(i,k).getColor());
                g.fillRect(x, y, Tiles.TILE_WIDTH, Tiles.TILE_HEIGHT);
            }
        }
    }

    public void differentTiles(ArrayList<Tiles> tiles, Tiles tile) {

        if (tiles.size() == 0 || tiles.get(0).getValue() == tile.getValue()) {
            if (!tiles.contains(tile)) {
                tiles.add(tile);
            } else {
                tiles.remove(tile);
            }
        } else {
            for (int i = tiles.size() - 1; i >= 0; i--) {
                tiles.get(i).setSelected(false);
                tiles.remove(tiles.get(i));
            }
            tiles.add(tile);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x, y;
        x = e.getX();
        y = e.getY();
        Tiles tile = game_board.getTiles_OnCoord(x, y);
        if (tile != null) {
            differentTiles(selected_tiles, tile);
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
