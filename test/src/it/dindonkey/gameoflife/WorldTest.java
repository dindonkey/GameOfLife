package it.dindonkey.gameoflife;

import org.junit.Test;

import static it.dindonkey.gameoflife.MatrixHelper.matrixCompare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by simone on 25/11/14.
 */

public class WorldTest {

    @Test
    public void countAliveNeighbors() throws Exception
    {
        int[][] expected = {{1,1,1,0},
                            {1,1,1,0},
                            {1,1,1,0},
                            {0,0,0,0}};
        World world = new World(expected);
        assertEquals(8, world.countAliveNeighbors(1, 1));
        assertEquals(3, world.countAliveNeighbors(0, 0));
        assertEquals(5, world.countAliveNeighbors(1, 0));
    }

    @Test
    public void blockPattern() throws Exception
    {
        int[][] expected = {{0,0,0,0},
                            {0,1,1,0},
                            {0,1,1,0},
                            {0,0,0,0},
        };

        World world = new World(expected);
        world.evolveToNextGeneration();

        assertTrue(matrixCompare(expected, world.currentGeneration));

    }

    @Test
    public void blinkerPattern() throws Exception
    {
        int[][] initial = {{0,0,0,0,0},
                           {0,0,0,0,0},
                           {0,1,1,1,0},
                           {0,0,0,0,0},
                           {0,0,0,0,0}};
        int[][] expected = {{0,0,0,0,0},
                            {0,0,1,0,0},
                            {0,0,1,0,0},
                            {0,0,1,0,0},
                            {0,0,0,0,0}};

        World world = new World(initial);
        world.evolveToNextGeneration();

        assertTrue(matrixCompare(expected, world.currentGeneration));
    }


}
