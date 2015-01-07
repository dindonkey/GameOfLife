package it.dindonkey.gameoflife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by simone on 07/01/15.
 */
public class LifePatternReader
{
    public static String fromFile(String path) throws IOException
    {
        BufferedReader reader = new BufferedReader( new FileReader(path));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }

        return stringBuilder.toString();
    }
}
