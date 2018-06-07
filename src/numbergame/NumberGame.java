package numbergame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author Yongcong
 */
public class NumberGame extends JPanel implements MouseListener {

    private int width;
    private int height;
    private GameBoard game_board;
    private ArrayList<Tiles> selected_tiles;
    private int numOfValidTiles;
    JFrame f = new JFrame();
    private Timer timer;
    private GameTimer gameTime;

    public NumberGame(int width, int height) {
        this(width, height, 0);
    }

    public NumberGame(int width, int height, int time) {
        this.width = width;
        this.height = height;
        game_board = new GameBoard(width, height);
        addMouseListener(this);
        setUpBoard();
        numOfValidTiles = width * height;
        selected_tiles = new ArrayList<Tiles>();
        gameTime = new GameTimer(time);
        checkTime();

    }

    public void checkTime() {
        ActionListener timeListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameTime.lost()) {
                    gameTime.removeTime();
                    repaint();
                } else {
                    timer.stop();
                    f.dispose();
                    new MainMenu();
                }
            }
        };
        timer = new Timer(1000, timeListener);
        timer.setInitialDelay(0);
        timer.start();
    }

    public void setUpBoard() {
        f.add(this);
        f.setSize(game_board.getWidth() + 15, game_board.getHeight() + 40);
        f.setTitle("Number Game");
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString(width +"X"+height, game_board.getWidth() / 2 - 15, 25);
        g.drawString(gameTime.timeString(), game_board.getWidth() / 2 - 25, 50);
        g.fillRect(0, GameTimer.HEIGHT, game_board.getWidth(), game_board.getHeight());
        drawBoard(game_board, g);
    }

    public void drawBoard(GameBoard gameboard, Graphics g) {
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                Tiles tile = gameboard.getTiles(i, k);

                int x = tile.getX();
                int y = tile.getY();

                int outlineX = x - Tiles.tile_outline_thickness;
                int outlineY = y - Tiles.tile_outline_thickness;
                int outLineWidth = Tiles.WIDTH + 2 * Tiles.tile_outline_thickness;
                int outLineHeight = Tiles.HEIGHT + 2 * Tiles.tile_outline_thickness;

                g.setColor(tile.isSelected());
                g.fillRect(outlineX, outlineY, outLineWidth, outLineHeight);

                g.setColor(gameboard.getTiles(i, k).getColor());
                g.fillRect(x, y, Tiles.WIDTH, Tiles.HEIGHT);
            }
        }
    }

    public void differentTiles(ArrayList<Tiles> tiles, Tiles tile) {
        if (tile.getRemoved()) {

        } else if (tiles.size() == 0 || tiles.get(0).getValue() == tile.getValue()) {
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
            int numOfValues = game_board.getTileValues().get(tile.getValue());
            if (numOfValues == selected_tiles.size() && !tile.getRemoved()) {
                numOfValidTiles -= selected_tiles.size();
                if (numOfValidTiles == 0) {
                    f.dispose();
                    timer.stop();
                    new NumberGame(width + 1, height + 1, gameTime.getTime());
                } else {
                    for (int i = 0; i < selected_tiles.size(); i++) {
                        selected_tiles.get(i).setRemoved();
                    }
                    selected_tiles.clear();
                }
            }
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
