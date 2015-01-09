package it.dindonkey.gameoflife;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by simone on 07/01/15.
 */
public class LIFFileReader
{
    public static LIFFile fromFile(InputStreamReader isReader) throws IOException
    {

        BufferedReader reader = new BufferedReader(isReader);
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        LIFFile lifFile = new LIFFile();

        int count = 0;
        while( ( line = reader.readLine() ) != null ) {
            if(count==1)
            {
                lifFile.name = line.replace("#D","").trim();
            }

            Pattern strokeStartingPointPattern = Pattern.compile("#P (-?\\d+) (-?\\d+)");
            Matcher matcher = strokeStartingPointPattern.matcher(line);
            if(matcher.matches())
            {
                lifFile.cols = Math.max(lifFile.cols, Math.abs(Integer.parseInt(matcher.group(1))) * 2);
                lifFile.rows = Math.max(lifFile.rows, Math.abs(Integer.parseInt(matcher.group(2))) * 2);
            }

            stringBuilder.append( line );
            stringBuilder.append( ls );
            count++;
        }


        lifFile.patterns = stringBuilder.toString();

        return lifFile;
    }
}
