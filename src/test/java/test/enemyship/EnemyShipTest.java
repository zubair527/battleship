package test.enemyship;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Test;

import com.battleship.persistence.impl.HsqlDBConnection;
import com.battleship.persistence.impl.EnemyShipDaoImpl;

public class EnemyShipTest
{

    @AfterClass
    public static void destroy()
    {
        HsqlDBConnection.closeConnection();
    }

    @Test
    public void test()
    {
        EnemyShipDaoImpl riddleDao = new EnemyShipDaoImpl();
        assertNotNull(riddleDao.getEnemyShip(1));
    }

    @Test
    public void testHintrandomness()
    {
        EnemyShipDaoImpl riddleDao = new EnemyShipDaoImpl();
        for (int level = 1; level < 10; level++) {
            String hinted = riddleDao.getEnemyshipLocationHint(level);
            System.out.println(hinted);
            int count = hinted.length() - hinted.replace("_", "").length();
            if (count < 1)
                fail();
        }
    }

}
