package it.dindonkey.gameoflife;

/**
 * Created by simone on 23/12/14.
 */
public class MatrixHelper
{
    public static boolean matrixCompare(int[][] matrixA, int[][] matrixB)
    {
        for(int j=0;j<10;j++)
        {
            for(int k=0;k<10;k++)
            {
                try
                {
                    if (matrixA[k][j] != matrixB[k][j])
                    {
                        return false;
                    }
                }catch (Exception e) {}
            }
        }

        return true;
    }
}
