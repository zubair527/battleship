package com.battleship.persistence.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.battleship.model.Ocean;
import com.battleship.model.Player;
import com.battleship.persistence.PlayerDao;

public class PlayerDaoImpl implements PlayerDao
{

    private Connection dbConnection;

    public PlayerDaoImpl()
    {
        this.dbConnection = HsqlDBConnection.getConnection();
    }

    public List<Player> getPlayers()
    {
        String sql = "Select * from player";
        try {

            ResultSet resultSet = dbConnection.createStatement().executeQuery(sql);
            List<Player> usersfromDB = new ArrayList<>();
            while (resultSet.next()) {

                Ocean chartData = null;
                try {
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(resultSet.getBytes("map")));
                    chartData = (Ocean) in.readObject();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                usersfromDB.add(new Player(resultSet.getString("name"), resultSet.getInt("level"),
                    resultSet.getInt("gems"), resultSet.getInt("health"), chartData));
            }
            return usersfromDB;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void persistPlayer(Player toSave)
    {
        String sql = "insert into player(name,level,gems,health,map) values(?,?,?,?,?)";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, toSave.getName());
            pstmt.setInt(2, toSave.getGems());
            pstmt.setInt(3, toSave.getLevel());
            pstmt.setInt(4, toSave.getHealth());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(toSave.getMap());
            pstmt.setBinaryStream(5, new ByteArrayInputStream(baos.toByteArray()));
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePlayer(Player player)
    {
        String sql = "update player set gems=?,health=?,map=?,level=? where name=?";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setInt(1, player.getGems());
            pstmt.setInt(2, player.getHealth());
            pstmt.setInt(4, player.getLevel());
            pstmt.setString(5, player.getName());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(player.getMap());
            pstmt.setBinaryStream(3, new ByteArrayInputStream(baos.toByteArray()));
            pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlayer(String player)
    {
        String sql = "delete from player where name=?";
        try {
            PreparedStatement pstmt = dbConnection.prepareStatement(sql);
            pstmt.setString(1, player);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
