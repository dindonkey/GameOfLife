package it.dindonkey.gameoflife;

/**
 * Created by simone on 25/11/14.
 */
public class World {

    public int[][] currentGeneration;
    private int numRow;
    private int numCol;

    public World(int numRow, int numCol)
    {
        this.numRow = numRow;
        this.numCol = numCol;
        this.currentGeneration = new int[numRow][numCol];
    }

    public World(int[][] startGeneration)
    {
        this.numCol = startGeneration.length;
        this.numRow = startGeneration[0].length;
        this.currentGeneration = startGeneration;
    }

    public void evolveToNextGeneration()
    {
        int nextGeneration[][] = new int[numRow][numCol];
        for(int j=0;j<numCol;j++)
        {
            for (int k = 0; k < numRow; k++)
            {
                Cell cell = new Cell(countAliveNeighbors(k,j));
                cell.status = cellStatus(k,j);
                cell.evolve();
                nextGeneration[k][j] = cell.status;
            }
        }
        currentGeneration = nextGeneration;
    }

    public int countAliveNeighbors(int x,int y)
    {

        return cellStatus(x-1,y-1)
                + cellStatus(x,y-1)
                + cellStatus(x+1,y-1)
                + cellStatus(x-1,y)
                + cellStatus(x+1,y)
                + cellStatus(x-1,y+1)
                + cellStatus(x,y+1)
                + cellStatus(x+1,y+1);
    }

    private int cellStatus(int x, int y)
    {
        try
        {
            return currentGeneration[x][y];
        }
        catch (Exception e)
        {
            return Cell.DEAD;
        }
    }

    public static void drawLifePattern(String pattern)
    {

    }

}
