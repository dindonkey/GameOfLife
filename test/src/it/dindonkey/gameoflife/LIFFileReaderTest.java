package it.dindonkey.gameoflife;

import org.junit.Test;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

/**
 * Remind: #N = #R 23/3
 */
public class LIFFileReaderTest
{
    @Test
    public void readLifFromFile() throws Exception
    {
        String expected =
                "#Life 1.05\n" +
                "#D p46 glider gun\n" +
                "#D Based on two shuttles which\n" +
                "#D make use of a double \n" +
                "#D B-heptomino reaction.  See\n" +
                "#D the p46 piston in OSCSPN2.\n" +
                "#N\n" +
                "#P 2 -17\n" +
                "**\n" +
                "**\n" +
                "#P -6 -8\n" +
                "..*\n" +
                ".***\n" +
                "**.*\n" +
                "#P 1 -8\n" +
                ".*\n" +
                "***\n" +
                "*.**\n" +
                "#P -25 4\n" +
                "..*\n" +
                "..**\n" +
                "*.***\n" +
                "....**\n" +
                "...**\n" +
                "...*\n" +
                ".\n" +
                "...*\n" +
                "...**\n" +
                "....**\n" +
                "*.***\n" +
                "..**\n" +
                "..*\n" +
                "#P -36 6\n" +
                "**\n" +
                "**\n" +
                "#P -9 13\n" +
                "**\n" +
                "**\n";

        LIFFile lifFile = LIFFileReader.fromFile(new FileReader(System.getProperty("user.dir") + "/assets/gol_patterns/GUN46.LIF"));
        assertEquals("p46 glider gun",lifFile.name);
        assertEquals(72,lifFile.cols);
        assertEquals(34, lifFile.rows);
        assertEquals(expected, lifFile.patterns);
    }


}
