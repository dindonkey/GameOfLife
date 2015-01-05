package it.dindonkey.gameoflife;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import static it.dindonkey.gameoflife.MatrixHelper.matrixCompare;

/**
 * Created by simone on 19/12/14.
 */
public class GolPattern
{
    public String name;
    public int[][] matrix;

    public GolPattern(String name, int[][] matrix)
    {
        this.name = name;
        this.matrix = matrix;
    }

    public GolPattern(String lifeFormat)
    {
        StringTokenizer tokenizer= new StringTokenizer(lifeFormat,"\n");
        name = tokenizer.nextToken().replace("#","");
        while(tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();

            Pattern p = Pattern.compile("^#P\\s.*$");
//            if(token.startsWith("#"))
        }
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GolPattern that = (GolPattern) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!matrixCompare(matrix,that.matrix)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }
}
