package com.battleship.persistence.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hsqldb.Server;

import com.battleship.utils.ConfigHelper;

public class HsqlDBConnection
{
    static Server hsqlServer = null;

    static Connection connection = null;

    ResultSet rs = null;

    private HsqlDBConnection()
    {

    }

    public static Connection getConnection()
    {
        if (connection == null) {
            Properties prop = ConfigHelper.loadDBProperties();
            try {
                startDatabase(prop.getProperty("database.name"), prop.getProperty("database.file"));

                Class.forName(prop.getProperty("database.DriverClass"));
                connection = DriverManager.getConnection(prop.getProperty("database.url"),
                    prop.getProperty("database.username"), prop.getProperty("database.password"));
                bootStrapData();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException impException) {
                System.err.println("If you are developer, Place enemy_ships.sql file in classpath");
                impException.printStackTrace();
            }
        }
        return connection;
    }

    private static void startDatabase(String dbname, String dbfilePath)
    {
        hsqlServer = new Server();
        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);
        hsqlServer.setDatabaseName(0, dbname);
        hsqlServer.setDatabasePath(0, dbfilePath);
        hsqlServer.start();
    }

    private static void bootStrapData() throws FileNotFoundException, IOException, SQLException
    {
        createTables();
        runSqlFile();
    }

    private static void createTables() throws SQLException
    {
        connection.prepareStatement(
            "create table if not exists player (id integer, name varchar(20) not null,level integer,gems integer,health integer,map BLOB);")
            .execute();
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, "enemy_ship".toUpperCase(), null);
        if (!tables.next()) {
            String shipsSql = "create table enemy_ship (difficulty integer,question varchar(500),answer varchar(100));";
            connection.createStatement().execute(shipsSql);
        }
        connection.commit();
    }

    private static void runSqlFile() throws FileNotFoundException, IOException, SQLException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
            HsqlDBConnection.class.getClassLoader().getResourceAsStream("enemy_ships.sql"), "UTF8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                connection.createStatement().execute(line);
            }
            connection.commit();
        }
    }

    public static void closeConnection()
    {
        hsqlServer.stop();
        hsqlServer.shutdown();
        hsqlServer = null;
        connection = null;
    }
}
