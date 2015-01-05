package it.dindonkey.gameoflife;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static it.dindonkey.gameoflife.Cell.ALIVE;
import static it.dindonkey.gameoflife.Cell.DEAD;

/**
 * Created by simone on 25/11/14.
 */

public class CellTest
{
    private Cell cell;

    @Test
    public void twoOrThreeNeighborsRule()
    {
        cell = new Cell(2);
        cell.status = ALIVE;
        cell.evolve();
        assertEquals(ALIVE,cell.status);

        cell = new Cell(3);
        cell.status = ALIVE;
        cell.evolve();
        assertEquals(ALIVE, cell.status);
    }

    @Test
    public void underPopulationRule() throws Exception
    {
        cell = new Cell(1);
        cell.status = ALIVE;
        cell.evolve();
        assertEquals(DEAD, cell.status);
    }

    @Test
    public void overCrowdingRule() throws Exception {

        cell = new Cell(4);
        cell.status = ALIVE;
        cell.evolve();
        assertEquals(DEAD,cell.status);
    }

    @Test
    public void birthRule() throws Exception
    {
        cell = new Cell(3);
        cell.status = DEAD;
        cell.evolve();
        assertEquals(ALIVE,cell.status);
    }
}
