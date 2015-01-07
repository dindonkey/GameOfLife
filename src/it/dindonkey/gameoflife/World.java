package it.dindonkey.gameoflife;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static it.dindonkey.gameoflife.Cell.ALIVE;

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

    public void drawLifePattern(String pattern)
    {
        int currentLine = 0;
        int patternStartX = 0;
        int patternStartY = 0;
        StringTokenizer tokenizer= new StringTokenizer(pattern,"\n");
        while(tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();

            Pattern strokeStartingPointPattern = Pattern.compile("#P (-?\\d+) (-?\\d+)");
            Matcher matcher = strokeStartingPointPattern.matcher(token);
            if(matcher.matches())
            {
                patternStartX = Integer.parseInt(matcher.group(1));
                patternStartY = Integer.parseInt(matcher.group(2));
                currentLine=0;
            }
            else if(!token.startsWith("#"))
            {
                char[] chars = token.toCharArray();
                for(int currentPos=0;currentPos<chars.length;currentPos++)
                {
                    if(chars[currentPos]=='*')
                    {
                        int strokeX = (numRow / 2) + patternStartX + currentPos;
                        int strokeY = (numCol / 2) + patternStartY + currentLine;
                        setCell(strokeX, strokeY, ALIVE);
                    }
                }
                currentLine++;
            }

        }
    }

    private void setCell(int x, int y, int status)
    {
        try
        {
            currentGeneration[x][y] = status;
        }catch (Exception e) {}
    }

}
