package com.battleship.persistence;

import java.util.List;

import com.battleship.model.Player;

public interface PlayerDao {
	public List<Player> getPlayers();
	public void persistPlayer(Player toSave);
	public void updatePlayer(Player player);
	public void deletePlayer(String name);
}
