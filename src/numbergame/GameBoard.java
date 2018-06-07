package numbergame;

import java.util.HashMap;

public class GameBoard {

    private int[][] gameboard;
    private int width, height;
    private Tiles[][] tiles;
    private int canvas_width = Tiles.PADDING;
    private int canvas_height = Tiles.PADDING + GameTimer.HEIGHT;
    private HashMap<Integer, Integer> tiles_value = new HashMap<>();

    public GameBoard(int width, int height) {
        gameboard = new int[width][height];
        tiles = new Tiles[width][height];
        this.width = width;
        this.height = height;
        generateGameBoard();
        getDimension();
    }

    public int getWidth() {
        return canvas_width;
    }

    public int getHeight() {
        return canvas_height;
    }

    public int[][] getGameBoard() {
        return gameboard;
    }

    public void setGameBoard(int[][] gameboard) {
        this.gameboard = gameboard;
    }

    public HashMap<Integer, Integer> getTileValues() {
        return tiles_value;
    }

    public void generateGameBoard() {
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                int x = i * (Tiles.WIDTH + Tiles.PADDING) + Tiles.PADDING;
                int y = k * (Tiles.WIDTH + Tiles.PADDING) + Tiles.PADDING + GameTimer.HEIGHT;
                gameboard[i][k] = (int) (Math.random() * Tiles.NUM_OF_COLORS + 1);
                tiles[i][k] = new Tiles(x, y, this.gameboard[i][k]);
                if (tiles_value.get(this.gameboard[i][k]) == null) {
                    tiles_value.put(this.gameboard[i][k], 1);
                } else {
                    int temp = tiles_value.get(this.gameboard[i][k]) + 1;
                    tiles_value.put(this.gameboard[i][k], temp);
                }
            }
        }
    }

    public void setGameState(int x, int y, int value) {
        gameboard[x][y] = value;
    }

    public Tiles[][] getTiles() {
        return tiles;
    }

    public Tiles getTiles(int index_X, int index_Y) {
        return tiles[index_X][index_Y];
    }

    public Tiles getTiles_OnCoord(int x, int y) {
        int total_tiles_width = (Tiles.WIDTH + Tiles.PADDING);
        int total_tiles_height = (Tiles.HEIGHT + Tiles.PADDING);
        int tiles_y = y - GameTimer.HEIGHT;
        if (tiles_y < 0 || x / total_tiles_width >= width || tiles_y / total_tiles_height >= height) {
            return null;
        }

        Tiles temp = tiles[x / total_tiles_width][tiles_y / total_tiles_height];

        if (x > temp.getX() && x < temp.getX() + Tiles.WIDTH && y > temp.getY() && y < temp.getY() + Tiles.HEIGHT) {
            temp.setSelected(!temp.getSelected());
            return temp;
        } else {
            return null;
        }
    }

    public void getDimension() {
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                Tiles tile = tiles[i][k];
                int tile_width = Tiles.WIDTH + Tiles.PADDING;
                int tile_height = Tiles.HEIGHT + Tiles.PADDING;

                int x = i * tile_width;
                int y = k * tile_height;

                if (i == 0) {
                    canvas_height += tile_height;
                }
                if (k == 0) {
                    canvas_width += tile_width;
                }
            }
        }
    }
}
