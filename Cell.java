public class Cell {
    
    private int x;
    private int y;
    private int dist;

    Cell(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDist() {
        return this.dist;
    }
}