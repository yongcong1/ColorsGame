package numbergame;

public class GameBoard {

    private int[][] gameboard;
    private int width, height;
    private Tiles[][] tiles;
    private int canvas_width = Tiles.PADDING;
    private int canvas_height = Tiles.PADDING;

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

    public void generateGameBoard() {
        for (int i = 0; i < width; i++) {
            for (int k = 0; k < height; k++) {
                int x = i * (Tiles.TILE_WIDTH + Tiles.PADDING) + Tiles.PADDING;
                int y = k * (Tiles.TILE_WIDTH + Tiles.PADDING) + Tiles.PADDING;
                gameboard[i][k] = (int) (Math.random() * Tiles.NUM_OF_COLORS + 1);
                tiles[i][k] = new Tiles(x, y, this.gameboard[i][k]);
            }
        }
    }

    public void setGameState(int x, int y, int value) {
        gameboard[x][y] = value;
    }

    public Tiles[][] getTiles() {
        return tiles;
    }
    
    public Tiles getTiles(int index_X, int index_Y){
        return tiles[index_X][index_Y];
    }

    public Tiles getTiles_OnCoord(int x, int y) {
        if(x / (Tiles.TILE_WIDTH + Tiles.PADDING) >= width || y / (Tiles.TILE_HEIGHT + Tiles.PADDING)>=height) return null;
        Tiles temp = tiles[x / (Tiles.TILE_WIDTH + Tiles.PADDING)][y / (Tiles.TILE_HEIGHT + Tiles.PADDING)];
        if (x > temp.getX() && x < temp.getX() + Tiles.TILE_WIDTH && y > temp.getY() && y < temp.getY() + Tiles.TILE_HEIGHT) {
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
                int tile_width = Tiles.TILE_WIDTH +Tiles.PADDING;
                int tile_height = Tiles.TILE_HEIGHT +Tiles.PADDING;

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
