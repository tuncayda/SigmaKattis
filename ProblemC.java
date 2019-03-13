import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.Queue;

public class ProblemC {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();   // Edge length
        int N = sc.nextInt();   // Maximum nr of steps
        int A = sc.nextInt();   // Starting cell ID
        int B = sc.nextInt();   // Target cell ID
        int X = sc.nextInt();   // Hardwax cell ID
        
        // Store all hardwax IDs. Set has faster access (tree-structure)
        HashSet<Integer> hardWaxSet = new HashSet<Integer>();
        for(int i = 0; i < X; i++) {
            hardWaxSet.add(sc.nextInt());
        }
        sc.close();

        // Start and end cells
        Cell startCell = null;
        Cell endCell = null;

        // The relation between the hexagon edge and the matrix representation
        int dimension = 2 * R - 1;
        
        // Matrix representation of the hexagonal grid (honeycomb).
        int[][] honeyMatrix = new int[dimension][dimension];
        int cellID = 1;
        
        // Transform hexagonal grid to a square matrix
        for(int r = 0; r < dimension; r++) {
            for(int c = 0; c < dimension; c++) {
                
                // Constraints on the movemenet range in the hexagonal grid
                if( c > (dimension-R-r-1) && c < (dimension+R-r-1) ) {
                    
                    // If the cell is not hardwax, it is valid 
                    if(!hardWaxSet.contains(cellID)) {
                        
                        // Give the starting and ending cells coordinates
                        if(cellID == A) {
                            startCell = new Cell(r,c,0);
                        } else if(cellID == B) {
                            endCell = new Cell(r,c,0);
                        }
                        honeyMatrix[r][c] = 1;
                    }
                    cellID++;
                }
            }
        }  
    
        int minDist = honeyPathLength(honeyMatrix, startCell, endCell, N);

        // Print the result
        if(minDist == -1) {
            System.out.println("No");
        } else {
            System.out.println(minDist);
        }
        
    }

    /**
     * Check if the next movement is valid.
     * Check that the distance is less than N and 
     * the cell is not visited and 
     * the cell is inside the honeycomb
     */
    private static boolean validMovement(int honeyMatrix[][], boolean visited[][], int row, int col, int dist, int N)
    {
        int dimension = honeyMatrix.length;
        return (dist <= N) && (row >= 0) && (row < dimension) && (col >= 0) && (col < dimension)
                       && honeyMatrix[row][col] == 1 && !visited[row][col];
    }
    
    /**
     * Using BFS to find the shortest path to the end cell i.e. find the honey.
     */
    private static int honeyPathLength(int honeyMatrix[][], Cell startCell, Cell endCell, int N)
    {

        int dimension = honeyMatrix.length;

        // All 6 possible movements in the hexagonal grid
        int rowMovements[] = { -1, 0, 1, 1, 0, -1};
        int colMovements[] = { 0, -1, -1, 0, 1, 1};
    
        // Visited cells; false as default
        boolean[][] visited = new boolean[dimension][dimension];

        // BFS queue
        Queue<Cell> q = new ArrayDeque<>();

        // Begin with the start cell
        visited[startCell.getX()][startCell.getY()] = true;
        q.add(startCell);

        int minDist = -1;

        while (!q.isEmpty())
        {
            Cell currentCell = q.poll();

            // We found the end cell i.e. the honey
            if (currentCell.getX() == endCell.getX() && currentCell.getY() == endCell.getY())
            {
                minDist = currentCell.getDist();
                break;
            }

            // Walk through all the neighbours
            for (int i = 0; i < 6; i++)
            {
                int nextX = currentCell.getX() + rowMovements[i];
                int nextY = currentCell.getY() + colMovements[i];
                int nextDist = currentCell.getDist() + 1;

                // Move only if the neighbour cell is valid and not visited
                if (validMovement(honeyMatrix, visited, nextX, nextY, nextDist, N))
                {
                    visited[nextX][nextY] = true;
                    q.add(new Cell(nextX, nextY, nextDist));
                }
            }
        }

        return minDist;
    }
}
