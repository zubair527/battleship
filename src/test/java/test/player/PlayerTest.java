package test.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.battleship.model.Player;
import com.battleship.persistence.impl.HsqlDBConnection;
import com.battleship.persistence.impl.PlayerDaoImpl;

public class PlayerTest
{
    static Connection db;

    @BeforeClass
    public static void setupDB()
    {
        db = HsqlDBConnection.getConnection();
        assertNotNull(db);
    }

    @AfterClass
    public static void destroy()
    {
        HsqlDBConnection.closeConnection();
    }

    @Test
    public void testAddPlayer()
    {
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        playerDao.persistPlayer(new Player("test"));
        List<Player> tPlayer = playerDao.getPlayers();
        assertTrue(tPlayer.get(0).getName().equals("test"));
    }

    @Test
    public void testDeletePlayer()
    {
        int testPlayerCount = 0;
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        playerDao.deletePlayer("test");
        List<Player> tPlayer = playerDao.getPlayers();
        for (Player p : tPlayer) {
            if (!p.getName().equals("test")) {
                testPlayerCount++;
            }
        }
        assertTrue(testPlayerCount == 0);
    }

}
