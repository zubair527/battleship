package test.player;

import java.sql.Connection;

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

    }

    @AfterClass
    public static void destroy()
    {
        HsqlDBConnection.closeConnection();
    }

    @Test
    public void testUserAdd()
    {
        PlayerDaoImpl userDao = new PlayerDaoImpl();
        userDao.persistPlayer(new Player("test"));
    }

    @Test
    public void testUserDelete()
    {
        PlayerDaoImpl userDao = new PlayerDaoImpl();
        userDao.deletePlayer("test");
    }

}
