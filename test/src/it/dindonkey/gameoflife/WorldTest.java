package it.dindonkey.gameoflife;

import org.junit.Test;

import static it.dindonkey.gameoflife.Cell.ALIVE;
import static it.dindonkey.gameoflife.Cell.DEAD;
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

    @Test
    public void drawSimplePattern() throws Exception
    {
        World world = new World(new int[10][10]);
        String pattern = "#P -2 -2\n" +
                         "**\n" +
                         "**\n";
        world.drawLifePattern(pattern);
        assertEquals(DEAD, world.currentGeneration[2][2]);
        assertEquals(ALIVE, world.currentGeneration[3][3]);
    }

    @Test
    public void drawComplexPattern() throws Exception
    {
        World world = new World(new int[10][10]);
        String pattern =
                "#P 1 1\n" +
                "..*\n" +
                ".***\n" +
                "**.*";
        world.drawLifePattern(pattern);
        assertEquals(DEAD, world.currentGeneration[6][7]);
        assertEquals(ALIVE, world.currentGeneration[6][8]);
    }

    @Test
    public void drawConsecutivePattern() throws Exception
    {
        World world = new World(new int[10][10]);
        String pattern =
                "#P -2 -2\n" +
                "**\n" +
                "**\n" +
                "#P 1 1\n" +
                "..*\n" +
                ".***\n" +
                "**.*";
        world.drawLifePattern(pattern);
        assertEquals(DEAD, world.currentGeneration[2][2]);
        assertEquals(ALIVE, world.currentGeneration[3][3]);
        assertEquals(DEAD, world.currentGeneration[6][7]);
        assertEquals(ALIVE, world.currentGeneration[6][8]);
    }

    @Test
    public void drawPatternsWithFullStuff() throws Exception
    {
        World world = new World(new int[10][10]);
        String pattern =
                "#Life 1.05\n" +
                "#D p46 glider gun\n" +
                "#D Based on two shuttles which\n" +
                "#D make use of a double \n" +
                "#D B-heptomino reaction.  See\n" +
                "#D the p46 piston in OSCSPN2.\n" +
                "#N\n" +
                "#P -2 -2\n" +
                "**\n" +
                "**\n" +
                "#P 1 1\n" +
                "..*\n" +
                ".***\n" +
                "**.*";
        world.drawLifePattern(pattern);
        assertEquals(DEAD, world.currentGeneration[2][2]);
        assertEquals(ALIVE, world.currentGeneration[3][3]);
        assertEquals(DEAD, world.currentGeneration[6][7]);
        assertEquals(ALIVE, world.currentGeneration[6][8]);

    }
}
