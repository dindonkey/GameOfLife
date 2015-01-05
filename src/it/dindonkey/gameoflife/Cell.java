package it.dindonkey.gameoflife;

/**
 * Created by simone on 25/11/14.
 */
public class Cell {
    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    private int neighbors;
    public int status;

    public Cell(int neighbors)
    {
        this.neighbors = neighbors;
    }

    public void evolve()
    {
        if((status==ALIVE))
        {
            if(neighbors==2 || neighbors==3)
            {
                status = ALIVE;
            }
            else if(neighbors < 2)
            {
                status = DEAD;
            }
            else if(neighbors >= 4)
            {
                status = DEAD;
            }
        }
        else
        {
            if(neighbors==3)
            {
                status = ALIVE;
            }
        }
    }
}
