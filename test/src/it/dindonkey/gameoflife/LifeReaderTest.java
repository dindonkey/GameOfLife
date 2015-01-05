package it.dindonkey.gameoflife;

import org.junit.Test;

import static it.dindonkey.gameoflife.MatrixHelper.matrixCompare;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Remind: #N = #R 23/3
 */
public class LifeReaderTest
{
    @Test
    public void testGolpFromString() throws Exception
    {
        GolPattern expected = new GolPattern("p46 glider gun",new int[][]{
                {1,1,0,0,0,0,0},
                {1,1,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0},
                {0,0,0,0,1,1,1},
                {0,0,0,1,1,0,1}
        });
        GolPattern actual = new GolPattern(
                        "#Life 1.05\n" +
                        "#D p46 glider gun\n" +
                        "#D Based on two shuttles which\n" +
                        "#D make use of a double \n" +
                        "#D B-heptomino reaction.  See\n" +
                        "#D the p46 piston in OSCSPN2.\n" +
                        "#N\n" +
                        "#P -2 -2\n" +
                        "**\n" +
                        "**" +
                        "#P 1 1\n" +
                        "..*\n" +
                        ".***\n" +
                        "**.*");
        assertEquals(expected,actual);
    }


}
